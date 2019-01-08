package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Class for testing tracker
 * @author shaplov
 * @since 08.01.2019
 */
public class StartUITest {
    private final PrintStream stdout = System.out;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final StringBuilder sMenu = new StringBuilder()
            .append("Menu.")
            .append(System.lineSeparator())
            .append("0. Add new Item")
            .append(System.lineSeparator())
            .append("1. Show all items")
            .append(System.lineSeparator())
            .append("2. Edit item")
            .append(System.lineSeparator())
            .append("3. Delete item")
            .append(System.lineSeparator())
            .append("4. Find item by Id")
            .append(System.lineSeparator())
            .append("5. Find items by name")
            .append(System.lineSeparator())
            .append("6. Exit Program")
            .append(System.lineSeparator());

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[] {"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteItemThenTrackerHasNotThisValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[] {"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenShowingAllItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[] {"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringBuilder()
                        .append(sMenu)
                        .append("------------ Showing all of the items ------------")
                        .append(System.lineSeparator())
                        .append("Here are all of the required items :")
                        .append(System.lineSeparator())
                        .append("№  Id            Name            Description")
                        .append(System.lineSeparator())
                        .append("-----------------------------------------------------")
                        .append(System.lineSeparator())
                        .append("1   ")
                        .append(item.getId())
                        .append(" test name       ")
                        .append("desc")
                        .append(System.lineSeparator())
                        .append("-----------------------------------------------------")
                        .append(System.lineSeparator())
                        .append(sMenu)
                        .toString()
                )
        );
    }

    @Test
    public void whenFindItemById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[] {"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringBuilder()
                        .append(sMenu)
                        .append("------------ Finding an item by Id ------------")
                        .append(System.lineSeparator())
                        .append("Id: ")
                        .append(item.getId())
                        .append(" Name: test name Description: desc")
                        .append(System.lineSeparator())
                        .append("-----------------------------------------------------")
                        .append(System.lineSeparator())
                        .append(sMenu)
                        .toString()
                )
        );
    }

    @Test
    public void whenFindItemsByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[] {"5", item.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringBuilder()
                        .append(sMenu)
                        .append("------------ Finding items by name ------------")
                        .append(System.lineSeparator())
                        .append("Here are all of the required items :")
                        .append(System.lineSeparator())
                        .append("№  Id            Name            Description")
                        .append(System.lineSeparator())
                        .append("-----------------------------------------------------")
                        .append(System.lineSeparator())
                        .append("1   ")
                        .append(item.getId())
                        .append(" test name       ")
                        .append("desc")
                        .append(System.lineSeparator())
                        .append("-----------------------------------------------------")
                        .append(System.lineSeparator())
                        .append(sMenu)
                        .toString()
                )
        );
    }
}
