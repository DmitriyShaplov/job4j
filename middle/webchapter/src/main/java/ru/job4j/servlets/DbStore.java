package ru.job4j.servlets;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.servlets.exceptions.RepeatedLoginException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Database implementation of Store.
 *
 * @author shaplov
 * @since 07.06.2019
 */
public class DbStore implements Store {

    private static final Logger LOG = LogManager.getLogger(DbStore.class.getName());

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    private DbStore() {
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/webdb");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("123456");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setMaxIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "insert into users(login, name, email, created) values(?, ?, ?, now())",
                     Statement.RETURN_GENERATED_KEYS
             )
        ) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getName());
            st.setString(3, user.getEmail());
            st.executeUpdate();
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(String.valueOf(rs.getInt(1)));
                    return user;
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            if (e.getMessage().contains("уже существует")) {
                throw new RepeatedLoginException("Such login already exists");
            }
        }
        throw new IllegalStateException("Could not add user to database");
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "update users set login=?, name=?, email=? where id=?"
             )
        ) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getName());
            st.setString(3, user.getEmail());
            st.setInt(4, Integer.parseInt(user.getId()));
            result = st.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            if (e.getMessage().contains("уже существует")) {
                throw new RepeatedLoginException("Such login already exists");
            }
        } catch (NumberFormatException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "delete from users where id=?"
             )) {
            st.setInt(1, Integer.parseInt(user.getId()));
            result = st.executeUpdate() > 0;
        } catch (SQLException | NumberFormatException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()
        ) {
            try (ResultSet rs = st.executeQuery("select * from users")) {
                while (rs.next()) {
                    String id = String.valueOf(rs.getInt("id"));
                    String name = rs.getString("name");
                    String login = rs.getString("login");
                    String email = rs.getString("email");
                    LocalDate date = rs.getTimestamp("created").toLocalDateTime().toLocalDate();
                    result.add(new User(id, name, login, email, date));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User findById(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("select * from users where id=?")
        ) {
            st.setInt(1, Integer.parseInt(user.getId()));
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String id = String.valueOf(rs.getInt("id"));
                    String name = rs.getString("name");
                    String login = rs.getString("login");
                    String email = rs.getString("email");
                    LocalDate date = rs.getTimestamp("created").toLocalDateTime().toLocalDate();
                    return new User(id, name, login, email, date);
                }
            }
        } catch (SQLException | NumberFormatException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException("There is no user with such id");
    }
}
