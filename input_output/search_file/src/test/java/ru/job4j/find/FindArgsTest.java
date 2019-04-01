package ru.job4j.find;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 01.04.2019
 */
public class FindArgsTest {

    @Test
    public void whenWrongArgumentsThenIsNotValid() {
        FindArgs findArgs = new FindArgs("-d -n -f -o".split(" ")).parse();
        assertFalse(findArgs.validate());
    }

    @Test
    public void whenGoodArgumentsThenIsValid() {
        FindArgs findArgs = new FindArgs("-d directory -n name -f -o log.txt".split(" ")).parse();
        assertTrue(findArgs.validate());
    }

    @Test
    public void whenWrongArgumentsThenGetErrors() {
        FindArgs findArgs = new FindArgs("-d dir -n name -o".split(" ")).parse();
        findArgs.validate();
        assertThat(findArgs.getErrors(), is(String.format("Run failed. Invalid arguments.%n"
                + "Please enter name type, log directory.")));
    }

    @Test
    public void whenGetDirectory() {
        FindArgs findArgs = new FindArgs("-d directory -n name -f -o log.txt".split(" ")).parse();
        assertThat(findArgs.directory(), is("directory"));
    }

    @Test
    public void whenGetName() {
        FindArgs findArgs = new FindArgs(" -n name -d directory -f -o log.txt".split(" ")).parse();
        assertThat(findArgs.name(), is("name"));
    }

    @Test
    public void whenGetType() {
        FindArgs findArgs = new FindArgs(" -n name -d directory -r -o log.txt".split(" ")).parse();
        assertThat(findArgs.type(), is(NameType.REGEXP));
    }

    @Test
    public void whenGetLogDirectory() {
        FindArgs findArgs = new FindArgs(" -n name -o log.txt -d directory -r ".split(" ")).parse();
        assertThat(findArgs.logDirectory(), is("log.txt"));
    }
}