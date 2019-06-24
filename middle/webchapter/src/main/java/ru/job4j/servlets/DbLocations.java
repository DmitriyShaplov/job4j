package ru.job4j.servlets;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shaplov
 * @since 24.06.2019
 */
public class DbLocations {

    private static final Logger LOG = LogManager.getLogger(DbLocations.class.getName());

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbLocations INSTANCE = new DbLocations();

    public static DbLocations getInstance() {
        return INSTANCE;
    }

    private DbLocations() {
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/webdb");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("123456");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setMaxIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public List<String> getCountries() {
        List<String> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()
        ) {
            try (ResultSet rs = st.executeQuery("select * from countries")) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    result.add(title);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public List<String> getCities(String country) {
        List<String> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "select ci.* from cities as ci inner join countries as co on ci.countries_id = co.id where co.title = ?")
            ) {
                ps.setString(1, country);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(rs.getString("title"));
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
