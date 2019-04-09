package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Tracker based on SQL database.
 * @author shaplov
 * @since 05.04.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class);
    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public TrackerSQL() {
    }

    /**
     * Init connection with database.
     * Creates table items if it is not exists.
     * @return not null connection
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            if (in == null) {
                throw new IllegalStateException("Could not get app.properties");
            }
            config.load(in);
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return this.connection != null;
    }

    /**
     * Creates db structure if tables is not exists.
     * @return result or throws IllegalStateException.
     */
    public boolean createStructure() {
        if (this.connection == null) {
            throw new IllegalStateException("No connection");
        }
        boolean result = false;
        try (Statement statement = this.connection.createStatement()) {
            result = statement.executeUpdate(
                    "create table if not exists items (id serial primary key not null, name varchar(200), description text, created timestamp)"
            ) > 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("Could not create database structure");
        }
        return result;
    }

    /**
     * Adds item into database.
     * @param item item
     * @return item if ok or throws IllegalStateException.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "insert into items(name, description, created) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesc());
            statement.setTimestamp(3, new Timestamp(item.getCreated()));
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    item.setId(String.valueOf(rs.getInt(1)));
                    return item;
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException("Could not create item");
    }

    /**
     * Replaces item in database.
     * @param id specified id
     * @param item new item
     * @return result.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "update items set name=?, description=?, created=? where id=?"
        )) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesc());
            statement.setTimestamp(3, new Timestamp(item.getCreated()));
            statement.setInt(4, Integer.valueOf(id));
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Deletes item from database.
     * @param id item's id
     * @return result
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from items where id=?"
        )) {
            statement.setInt(1, Integer.valueOf(id));
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Gets list of items from database.
     * @return list of items.
     */
    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(
                    "select * from items"
            )) {
                while (rs.next()) {
                    Item item = new Item(rs.getString("name"), rs.getString("description"),
                            rs.getTimestamp("created").getTime());
                    item.setId(String.valueOf(rs.getInt("id")));
                    list.add(item);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Gets items by name.
     * @param key String name
     * @return list of items
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(
            "select * from items where name=?"
        )) {
            statement.setString(1, key);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(rs.getString("name"), rs.getString("description"),
                            rs.getTimestamp("created").getTime());
                    item.setId(String.valueOf(rs.getInt("id")));
                    list.add(item);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Gets item by id.
     * @param id String name
     * @return item or null
     */
    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "select * from items where id=?"
        )) {
            statement.setInt(1, Integer.valueOf(id));
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    item = new Item(rs.getString("name"), rs.getString("description"),
                            rs.getTimestamp("created").getTime());
                    item.setId(String.valueOf(rs.getInt("id")));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * Close connection.
     * @throws Exception e
     */
    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            connection.close();
        }
    }
}
