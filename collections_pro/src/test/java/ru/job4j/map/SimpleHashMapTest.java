package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

/**
 * @author shaplov
 * @since 11.03.2019
 */
public class SimpleHashMapTest {

    @Test
    public void whenInsertPairThenUseGetValueResultValue() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "test");
        assertThat(map.size(), is(1));
        assertThat(map.get(1), is("test"));
    }

    @Test
    public void whenInsertThenDeleteResultZeroSizeAndNullElement() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "test");
        boolean res1 = map.delete(1);
        boolean res2 = map.delete(1);
        assertThat(map.size(), is(0));
        assertNull(map.get(1));
        assertThat(res1 && !res2, is(true));
    }

    @Test
    public void whenInsertThreeElementsThenUseIteratorAndAddValuesToArrayListResultListContainsAllValues() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "test1");
        map.insert(2, "test2");
        map.insert(3, "test3");
        var it = map.iterator();
        List<String> list = new ArrayList<>();
        while (it.hasNext()) {
            list.add(it.next().getValue());
        }
        boolean result = list.contains("test1")
                && list.contains("test2")
                && list.contains("test3");
        assertThat(result, is(true));
    }

    @Test
    public void whenInsertTwoEqualValuesThenResultFalseAndSizeOne() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        boolean res1 = map.insert(1, "test");
        boolean res2 = map.insert(1, "test");
        assertThat(res1 && !res2, is(true));
    }

    @Test
    public void whenConstructorWith2SizeThenAddThreeElementsResultDynamicArray() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(2);
        map.insert(1, "test1");
        map.insert(2, "test2");
        map.insert(3, "test3");
        assertThat(map.get(3), is("test3"));
    }
}