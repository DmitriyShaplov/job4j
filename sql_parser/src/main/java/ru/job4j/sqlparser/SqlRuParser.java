package ru.job4j.sqlparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * @author shaplov
 * @since 09.04.2019
 */
public class SqlRuParser implements AutoCloseable {

    public static final Logger LOG = LogManager.getLogger(SqlRuParser.class.getName());

    private Connection connection;

    private LocalDateTime lastDateTime;
    private static final DateTimeFormatter FMT;
    private static final Map<Long, String> MONTH_NAME_MAP = new HashMap<>();

    private Properties config;

    static {
        MONTH_NAME_MAP.put(1L, "янв");
        MONTH_NAME_MAP.put(2L, "фев");
        MONTH_NAME_MAP.put(3L, "мар");
        MONTH_NAME_MAP.put(4L, "апр");
        MONTH_NAME_MAP.put(5L, "май");
        MONTH_NAME_MAP.put(6L, "июн");
        MONTH_NAME_MAP.put(7L, "июл");
        MONTH_NAME_MAP.put(8L, "авг");
        MONTH_NAME_MAP.put(9L, "сен");
        MONTH_NAME_MAP.put(10L, "окт");
        MONTH_NAME_MAP.put(11L, "ноя");
        MONTH_NAME_MAP.put(12L, "дек");
        FMT = new DateTimeFormatterBuilder()
                .appendPattern("d ")
                .appendText(ChronoField.MONTH_OF_YEAR, MONTH_NAME_MAP)
                .appendPattern(" yy, HH:mm")
                .toFormatter();
    }

    public SqlRuParser(Properties config, LocalDateTime lastDateTime) {
        this.config = config;
        this.lastDateTime = lastDateTime;
    }

    /**
     * Init connection to database.
     */
    public boolean init() {
        try {
            this.connection = DriverManager.getConnection(
                    this.config.getProperty("jdbc.url"),
                    this.config.getProperty("jdbc.username"),
                    this.config.getProperty("jdbc.password")
            );
            this.connection.setAutoCommit(false);
        } catch (Exception e) {
            throw new IllegalStateException("Could not connect to database.");
        }
        return this.connection != null;
    }

    /**
     * Store data to database.
     * @param name name
     * @param text text
     * @param link link
     */
    public void storeData(String name, String text, String link) {
        if (this.connection == null) {
            throw new IllegalStateException("No connection to database.");
        }
        try (PreparedStatement statement = this.connection.prepareStatement(
                "insert into vacancy(name, text, link) values(?, ?, ?) on conflict do nothing")
        ) {
            statement.setString(1, name);
            statement.setString(2, text);
            statement.setString(3, link);
            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            if (this.connection != null) {
                try {
                    this.connection.rollback();
                } catch (SQLException e1) {
                    throw new IllegalStateException(e1);
                }
            }
            throw new IllegalStateException(e);
        }
    }

    /**
     * Gets pages count.
     */
    private int pagesCount() throws IOException {
        Document firstPage = Jsoup.connect("https://www.sql.ru/forum/job-offers/1").get();
        Element pages = firstPage.select("table.sort_options").last();
        return Integer.valueOf(pages.select("a").last().text());
    }

    /**
     * Parsing pages.
     */
    public LocalDateTime parsePages() {
        if (this.connection == null) {
            throw new IllegalStateException("No connection");
        }
        LocalDateTime startParseDateTime = LocalDateTime.now();
        try {
            boolean bNewData = true;
            int pageCount = pagesCount();
            for (int page = 1; page < pageCount && bNewData; ++page) {
                Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + page).get();
                Element table = doc.selectFirst("table.forumTable");
                Elements rows = table.select("tr:has(.postslisttopic:not(:contains(Важно)))");
                if (page == 1) {
                    String firstRowDate = rows.first().select("td").get(5).text();
                    startParseDateTime = getDateTime(firstRowDate);
                }
                Elements filteredRows = rows.select("tr:has(.postslisttopic:contains(Java)):not(:contains(JavaScript)):not(:contains(Java Script)):not(:contains(JS))");
                if (filteredRows.size() == 0) {
                    if (getDateTime(rows.first().select("td").get(5).text()).isBefore(this.lastDateTime)) {
                        break;
                    }
                } else {
                    for (Element row : filteredRows) {
                        Elements cols = row.select("td");
                        String link = cols.get(1).select("a").attr("href");
                        Document subDoc = Jsoup.connect(link).get();
                        String text = subDoc.selectFirst("table.msgTable").select("td.msgBody").get(1).text();
                        String name = cols.get(1).text();
                        String strDate = cols.get(5).text();
                        if (getDateTime(strDate).isBefore(this.lastDateTime)) {
                            bNewData = false;
                            break;
                        }
                        storeData(name, text, link);
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return startParseDateTime;
    }

    /**
     * Get DateTime from String.
     */
    private LocalDateTime getDateTime(String strDate) {
        LocalDateTime dateTime;
        if (strDate.matches("^сегодня, [0-2][0-9]:[0-5][0-9]$")) {
            LocalTime localTime = LocalTime.parse(strDate.split(" ")[1]);
            dateTime = LocalDate.now().atTime(localTime);
        } else if (strDate.matches("^вчера, [0-2][0-9]:[0-5][0-9]$")) {
            LocalTime localTime = LocalTime.parse(strDate.split(" ")[1]);
            dateTime = LocalDate.now().minusDays(1).atTime(localTime);
        } else {
            dateTime = LocalDateTime.parse(strDate, FMT);
        }
        return dateTime;
    }

    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }
}
