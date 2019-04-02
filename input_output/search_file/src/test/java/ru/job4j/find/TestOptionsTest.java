package ru.job4j.find;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TestOptionsTest {

    @Test
    public void whenWrongArgumentsThenIsNotValid() {
        TestOptions findArgs = new TestOptions("-d -n -f -o".split(" "));
        assertFalse(findArgs.isValid());
    }

    @Test
    public void whenGoodArgumentsThenIsValid() {
        TestOptions findArgs = new TestOptions("-d directory -n name -f -o log.txt".split(" "));
        assertTrue(findArgs.isValid());
    }

//    @Test
//    public void whenWrongArgumentsThenGetErrors() {
//        TestOptions findArgs = new TestOptions("-d dir -n name -o".split(" "));
//        assertThat(findArgs.getErrors(), is(String.format("Run failed. Invalid arguments.%n"
//                + "Please enter name type, log directory.")));
//    }

    @Test
    public void whenGetDirectory() {
        TestOptions findArgs = new TestOptions("-d directory -n name -f -o log.txt".split(" "));
        assertThat(findArgs.directory(), is("directory"));
    }

    @Test
    public void whenGetName() {
        TestOptions findArgs = new TestOptions(" -n name -d directory -f -o log.txt".split(" "));
        assertThat(findArgs.name(), is("name"));
    }

    @Test
    public void whenGetType() {
        TestOptions findArgs = new TestOptions(" -n name -d directory -r -o log.txt".split(" "));
        assertThat(findArgs.type(), is(NameType.REGEXP));
    }

    @Test
    public void whenGetLogDirectory() {
        TestOptions findArgs = new TestOptions(" -n name -o log.txt -d directory -r ".split(" "));
        assertThat(findArgs.logDirectory(), is("log.txt"));
    }
}