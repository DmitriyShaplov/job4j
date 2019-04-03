package ru.job4j.tracker.singleton;

import org.junit.Test;
import ru.job4j.tracker.Item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class TrackerSingleTest {

    @Test
    public void whenTrackerSingleEnum() {
        TrackerSingleEnum tracker = TrackerSingleEnum.TRACKER_INSTANCE;
        tracker.add(new Item("testName", "testDesc"));
        TrackerSingleEnum tracker2 = TrackerSingleEnum.TRACKER_INSTANCE;
        assertThat(tracker2.findAdd().get(0).getDesc(), is("testDesc"));
    }

    @Test
    public void whenTrackerStaticField() {
        TrackerSingleStaticField tracker = TrackerSingleStaticField.getInstance();
        tracker.add(new Item("testName", "testDesc"));
        TrackerSingleStaticField tracker2 = TrackerSingleStaticField.getInstance();
        assertThat(tracker2.findAdd().get(0).getDesc(), is("testDesc"));
    }

    @Test
    public void whenTrackerStaticFinalField() {
        TrackerSingleFinalField tracker = TrackerSingleFinalField.getInstance();
        tracker.add(new Item("testName", "testDesc"));
        TrackerSingleFinalField tracker2 = TrackerSingleFinalField.getInstance();
        assertThat(tracker2.findAdd().get(0).getDesc(), is("testDesc"));
    }

    @Test
    public void whenTrackerStaticFinalClassField() {
        TrackerSingleFinalClass tracker = TrackerSingleFinalClass.getInstance();
        tracker.add(new Item("testName", "testDesc"));
        TrackerSingleFinalClass tracker2 = TrackerSingleFinalClass.getInstance();
        assertThat(tracker2.findAdd().get(0).getDesc(), is("testDesc"));
    }
}