package ru.job4j.streamapi;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertMatrixToListTest {

    @Test
    public void whenConvertListOfListThenListOfInteger() {
        ConvertMatrixToList convertMatrixToList = new ConvertMatrixToList();
        var list = List.of(
                List.of(1, 2),
                List.of(3, 4)
        );
        var result = convertMatrixToList.convert(list);
        var expect = List.of(1, 2, 3, 4);
        assertThat(result, is(expect));
    }
}
