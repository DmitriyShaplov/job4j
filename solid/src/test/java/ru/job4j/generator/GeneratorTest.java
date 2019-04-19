package ru.job4j.generator;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test for generator
 */
public class GeneratorTest {

    @Test
    public void whenGenerateFirstExampleThenFormattedString() {
        Generator generator = new SimpleGenerator();
        String result = generator.generate("I am ${name}, Who are ${subject}?",
                Map.of("name", "Dmitriy", "subject", "you"));
        assertThat(result, is("I am Dmitriy, Who are you?"));
    }

    @Test
    public void whenGenerateSecondExampleThenFormattedString() {
        Generator generator = new SimpleGenerator();
        String result = generator.generate("${name} like ${some}, ${some} doesnt like ${name}.",
                Map.of("name", "Marty", "some", "Maria"));
        assertThat(result, is("Marty like Maria, Maria doesnt like Marty."));
    }

    @Test(expected = IllegalStateException.class)
    public void whenGenerateExampleWithoutOneKeyThenException() {
        Generator generator = new SimpleGenerator();
        String result = generator.generate("${name}, ${okay}", Map.of("name", "replaced"));
    }

    @Test(expected = IllegalStateException.class)
    public void whenGenerateExampleWithExtraKeysInMapThenException() {
        Generator generator = new SimpleGenerator();
        String result = generator.generate("${name} - good name", Map.of("name", "replaced", "subject", "empty"));
    }
}