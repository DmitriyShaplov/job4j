package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Test methods from Tracker Class
 * @author shaplov
 * @since 24.12.2018
 * @version 1.00
 */
public class TrackerTest {
    /**
     * заменяем местами нужную нам заявку по id
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * получаем массив заявок без null элементов
     */
    @Test
    public void whenAddThreeAndFindAllThenArrayLengthThree() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDesc1");
        tracker.add(item);
        item = new Item("test2", "testDesc2");
        tracker.add(item);
        item = new Item("test3", "testDesc3");
        tracker.add(item);
        Item[] result = tracker.findAll();
        String resultName = result[2].getName();
        int expectLength = 3;
        String expectName = "test3";
        assertThat(result.length, is(expectLength));
        assertThat(resultName, is(expectName));
    }

    /**
     * получаем массив заявок с требуемым именем
     */
    @Test
    public void whenAddThreeAndFindByNameThenArrayLengthTwo() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDesc1");
        tracker.add(item);
        item = new Item("test1", "testDesc2");
        tracker.add(item);
        item = new Item("test3", "testDesc3");
        tracker.add(item);
        Item[] result = tracker.findByName("test1");
        int expectLength = 2;
        assertThat(result.length, is(expectLength));
    }

    /**
     * находим заявку по id
     */
    @Test
    public void whenFindByIdThenExpectedId() {
        Tracker tracker = new Tracker();
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

    /**
     * Удаляем элемент в середине массива заявок
     */
    @Test
    public void whenDeleteItemInMiddleThenArrayLengthDecreasesAndCantFindItemAndLastItemNewEqualsLastItemOld() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDesc1");
        tracker.add(item);
        item = new Item("test2", "testDesc2");
        tracker.add(item);
        String deletingId = item.getId();
        item = new Item("test3", "testDesc3");
        tracker.add(item);
        Item[] oldItems = tracker.findAll();
        int oldLength = oldItems.length;
        String oldName = oldItems[oldItems.length - 1].getName();
        tracker.delete(deletingId);
        Item[] newItems = tracker.findAll();
        int newLength = newItems.length;
        String newName = newItems[newItems.length - 1].getName();
        Item findResult = tracker.findById(deletingId);
        assertNull(findResult);
        assertThat(newLength, is(oldLength - 1));
        assertThat(newName, is(oldName));
    }

    /**
     * удаляем элемент в конце массива заявок
     */
    @Test
    public void whenDeleteLastItemThenArrayLengthDecreasesAndCantFindItemAndLastItemNewEqualsPreviousItemOld() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDesc1");
        tracker.add(item);
        item = new Item("test2", "testDesc2");
        tracker.add(item);
        item = new Item("test3", "testDesc3");
        tracker.add(item);
        String deletingId = item.getId();
        Item[] oldItems = tracker.findAll();
        int oldLength = oldItems.length;
        String oldName = oldItems[oldItems.length - 2].getName();
        tracker.delete(deletingId);
        Item[] newItems = tracker.findAll();
        int newLength = newItems.length;
        String newName = newItems[newItems.length - 1].getName();
        Item findResult = tracker.findById(deletingId);
        assertNull(findResult);
        assertThat(newLength, is(oldLength - 1));
        assertThat(newName, is(oldName));
    }
}
