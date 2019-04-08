package ru.job4j.store;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreXMLTest {

    @Test
    public void whenCreateXMLFile() throws IOException {
        File target = new File(System.getProperty("java.io.tmpdir")
        + File.separator + "xmlTest.xml");
        StoreXML storeXML = new StoreXML(target);
        List<Entry> entries = List.of(
                new Entry(1),
                new Entry(2),
                new Entry(3)
        );
        storeXML.save(entries);
        String xmlExpect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<entries>\n"
                + "    <entry>\n"
                + "        <field>1</field>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <field>2</field>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <field>3</field>\n"
                + "    </entry>\n"
                + "</entries>\n";
        String xmlResult = Files.readString(target.toPath());
        assertThat(xmlResult, is(xmlExpect));
        Files.deleteIfExists(target.toPath());
    }
}