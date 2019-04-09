package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 06.04.2019
 */
public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkConnection() throws Exception {
        try (TrackerSQL sql = new TrackerSQL()) {
            assertThat(sql.init(), is(true));
        }
    }

    @Test
    public void createItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void whenReplaceNameThenReturnNewName() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item previous = new Item("test1", "testDescription");
            tracker.add(previous);
            Item next = new Item("test2", "testDescription2");
            next.setId(previous.getId());
            tracker.replace(previous.getId(), next);
            assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
        }
    }

    @Test
    public void whenAddThreeAndFindAllThenArrayLengthThree() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test1", "testDesc1");
            tracker.add(item);
            item = new Item("test2", "testDesc2");
            tracker.add(item);
            item = new Item("test3", "testDesc3");
            tracker.add(item);
            List<Item> result = tracker.findAll();
            String resultName = result.get(2).getName();
            int expectLength = 3;
            String expectName = "test3";
            assertThat(result.size(), Is.is(expectLength));
            assertThat(resultName, is(expectName));
        }
    }

    @Test
    public void whenAddThreeAndFindByNameThenArrayLengthTwo() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test1", "testDesc1");
            tracker.add(item);
            item = new Item("test1", "testDesc2");
            tracker.add(item);
            item = new Item("test3", "testDesc3");
            tracker.add(item);
            List<Item> result = tracker.findByName("test1");
            int expectLength = 2;
            assertThat(result.size(), is(expectLength));
        }
    }

    @Test
    public void whenFindByIdThenExpectedId() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test1", "testDesc1");
            tracker.add(item);
            item = new Item("test2", "testDesc2");
            tracker.add(item);
            String expectedId = item.getId();
            item = new Item("test3", "testDesc3");
            tracker.add(item);
            Item result = tracker.findById(expectedId);
            String resultId = result.getId();
            assertThat(resultId, is(expectedId));
        }
    }

    @Test
    public void whenDeleteItemInMiddleThenArrayLengthDecreasesAndCantFindItemAndLastItemNewEqualsLastItemOld()
            throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test1", "testDesc1");
            tracker.add(item);
            item = new Item("test2", "testDesc2");
            tracker.add(item);
            String deletingId = item.getId();
            item = new Item("test3", "testDesc3");
            tracker.add(item);
            List<Item> oldItems = tracker.findAll();
            int oldLength = oldItems.size();
            String oldName = oldItems.get(oldItems.size() - 1).getName();
            tracker.delete(deletingId);
            List<Item> newItems = tracker.findAll();
            int newLength = newItems.size();
            String newName = newItems.get(newItems.size() - 1).getName();
            Item findResult = tracker.findById(deletingId);
            assertNull(findResult);
            assertThat(newLength, is(oldLength - 1));
            assertThat(newName, is(oldName));
        }
    }

    @Test
    public void whenDeleteLastItemThenArrayLengthDecreasesAndCantFindItemAndLastItemNewEqualsPreviousItemOld()
            throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test1", "testDesc1");
            tracker.add(item);
            item = new Item("test2", "testDesc2");
            tracker.add(item);
            item = new Item("test3", "testDesc3");
            tracker.add(item);
            String deletingId = item.getId();
            List<Item> oldItems = tracker.findAll();
            int oldLength = oldItems.size();
            String oldName = oldItems.get(oldItems.size() - 2).getName();
            tracker.delete(deletingId);
            List<Item> newItems = tracker.findAll();
            int newLength = newItems.size();
            String newName = newItems.get(newItems.size() - 1).getName();
            Item findResult = tracker.findById(deletingId);
            assertNull(findResult);
            assertThat(newLength, is(oldLength - 1));
            assertThat(newName, is(oldName));
        }
    }
}