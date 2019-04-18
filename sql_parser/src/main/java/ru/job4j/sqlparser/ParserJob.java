package ru.job4j.sqlparser;

import org.quartz.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

/**
 * @author shaplov
 * @since 11.04.2019
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ParserJob implements Job {

    public static final String DATE_TIME = "date_time";
    public static final String PROPERTIES = "properties";

    public ParserJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        Properties config = (Properties) data.get(PROPERTIES);
        LocalDateTime dateTime = (LocalDateTime) data.get(DATE_TIME);
        try (SqlRuParser parser = new SqlRuParser(config, dateTime)) {
            SqlRuParser.LOG.info("Starting parse at " + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            SqlRuParser.LOG.info("Last date time parsed: " + dateTime);
            parser.init();
            dateTime = parser.parsePages();
        } catch (Exception e) {
            SqlRuParser.LOG.error(e.getMessage(), e);
            JobExecutionException e2 = new JobExecutionException(e);
            e2.refireImmediately();
            throw e2;
        }
        data.put(DATE_TIME, dateTime);
    }
}
