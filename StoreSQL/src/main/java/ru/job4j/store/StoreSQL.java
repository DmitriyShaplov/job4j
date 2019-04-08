package ru.job4j.store;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for generate entry table values
 * and put it to List of Entry.
 * @author shaplov
 * @since 08.04.2019
 */
public class StoreSQL implements AutoCloseable {

    private static final String FS = File.separator;

    private final Config config;
    private Connection connect;

    public StoreSQL(final Config config) {
        this.config = config;
    }

    /**
     * init connection to db.
     */
    public boolean init() {
        String url = config.get("url");
        url = url.replace("/", FS);
        try {
            this.connect = DriverManager.getConnection(url);
            this.connect.setAutoCommit(false);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connect != null;
    }

    /**
     * Generate table data.
     */
    public void generate(int size) {
        try (Statement st = this.connect.createStatement()) {
            st.executeUpdate("create table if not exists entry (field integer)");
            st.executeUpdate("delete from entry");
            try (PreparedStatement ps =
                         this.connect.prepareStatement("insert into entry(field) values(?)")) {
                for (int i = 1; i <= size; ++i) {
                    ps.setInt(1, i);
                    ps.addBatch();
                }
                ps.executeBatch();
                this.connect.commit();
            }
        } catch (Exception e) {
            if (this.connect != null) {
                try {
                    this.connect.rollback();
                } catch (SQLException e1) {
                    throw new IllegalStateException(e1);
                }
            }
            throw new IllegalStateException("Could not create entry table");
        }
    }

    /**
     * load entry table to entries list.
     */
    public List<Entry> load() {
        List<Entry> list = new ArrayList<>();
        try (Statement st = this.connect.createStatement()) {
            try (ResultSet rs = st.executeQuery("select field from entry")) {
                while (rs.next()) {
                    list.add(new Entry(rs.getInt("field")));
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (this.connect != null) {
            this.connect.close();
        }
    }
}
