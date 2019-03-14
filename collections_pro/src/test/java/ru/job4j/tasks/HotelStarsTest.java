package ru.job4j.tasks;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class HotelStarsTest {

    @Test
    public void whenMakeStarsThenIntArray() {
        HotelStars hotelStars = new HotelStars();
        var result = hotelStars.makeStars(99, 2, 100, 50, 1);
        var expected = new int[] {4, 2, 5, 3, 1};
        assertThat(result, is(expected));
    }

    @Test
    public void whenMakeStarsThenIntArray2() {
        HotelStars hotelStars = new HotelStars();
        var result = hotelStars.makeStars(100, 90, 10, 20, 50, 60, 40, 30, 80, 70);
        var expected = new int[] {5, 5, 1, 1, 3, 3, 2, 2, 4, 4};
        assertThat(result, is(expected));
    }
}