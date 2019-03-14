package ru.job4j.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramTest {

    @Test
    public void whenAnagram() {
        Anagram anagram = new Anagram();
        boolean result = anagram.isAnagram("инкаснеж", "снежинка");
        assertTrue(result);
    }

    @Test
    public void whenNotAnagramDifferentCharCount() {
        Anagram anagram = new Anagram();
        boolean result = anagram.isAnagram("инкасееж", "инкассеж");
        assertFalse(result);
    }

    @Test
    public void whenNotAnagram() {
        Anagram anagram = new Anagram();
        boolean result = anagram.isAnagram("инкаснеЖ", "снежинка");
        assertFalse(result);
    }
}