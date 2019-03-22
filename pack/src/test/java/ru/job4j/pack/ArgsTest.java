package ru.job4j.pack;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArgsTest {

    @Test
    public void whenParsingArgsThenDirectoryOutputExcule() {
        Args args = new Args(new String[] {"-o", "project.zip", "-e", "java", "xml", "-d", "d:\\projects"});
        String expectOut = "project.zip";
        String expectDir = "d:\\projects";
        List<String> expectExc = List.of("java", "xml");
        assertThat(args.directory(), is(expectDir));
        assertThat(args.output(), is(expectOut));
        assertThat(args.exclude(), is(expectExc));
    }
}