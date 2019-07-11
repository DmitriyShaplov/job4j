package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.Seat;
import ru.job4j.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author shaplov
 * @since 26.06.2019
 */
public class DbStore implements Store {

    private static final Logger LOG = LogManager.getLogger(DbStore.class.getName());

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private final static Store INSTANCE = new DbStore();

    private DbStore() {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            if (in == null) {
                throw new IllegalStateException("Could not get app.properties");
            }
            config.load(in);
            SOURCE.setUrl(config.getProperty("jdbc.url"));
            SOURCE.setUsername(config.getProperty("jdbc.username"));
            SOURCE.setPassword(config.getProperty("jdbc.password"));
            SOURCE.setDriverClassName(config.getProperty("jdbc.driver"));
            SOURCE.setMinIdle(Integer.parseInt(config.getProperty("jdbc.minIdle")));
            SOURCE.setMaxIdle(Integer.parseInt(config.getProperty("jdbc.maxIdle")));
            SOURCE.setMaxOpenPreparedStatements(Integer.parseInt(config.getProperty("jdbc.maxOPS")));
        } catch (IOException e) {
            throw new IllegalStateException("Could not get app.properties");
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Wrong format in app.properties");
        }
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Seat> getSeats() {
        List<Seat> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(
                    "select * from halls"
            )) {
                while (rs.next()) {
                    int row = rs.getInt("row");
                    int place = rs.getInt("place");
                    int price = rs.getInt("price");
                    boolean sold = rs.getBoolean("sold");
                    Seat seat = new Seat(row, place, price, sold);
                    result.add(seat);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Seat getSeat(Seat seat) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from halls where row=? and place=?")) {
            ps.setInt(1, seat.getRow());
            ps.setInt(2, seat.getPlace());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int row = rs.getInt("row");
                    int place = rs.getInt("place");
                    int price = rs.getInt("price");
                    boolean sold = rs.getBoolean("sold");
                    return new Seat(row, place, price, sold);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException("There is no seat with this parameters");
    }

    @Override
    public boolean makePayment(Seat seat, User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps1 = connection.prepareStatement(
                    "select * from halls where row=? and place=?"
            )) {
                ps1.setInt(1, seat.getRow());
                ps1.setInt(2, seat.getPlace());
                ResultSet rs = ps1.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    try (PreparedStatement ps2 = connection.prepareStatement("update halls set sold=? where id=?")) {
                        ps2.setBoolean(1, true);
                        ps2.setInt(2, id);
                        result = ps2.executeUpdate() == 1;
                    }
                    if (result) {
                        try (PreparedStatement ps3 = connection.prepareStatement(
                                "insert into accounts (username, phone, halls_id) values (?, ?, ?)"
                        )) {
                            ps3.setString(1, user.getUsername());
                            ps3.setString(2, user.getPhone());
                            ps3.setInt(3, id);
                            ps3.executeUpdate();
                        }
                        connection.commit();
                    } else {
                        connection.rollback();
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
