package ru.job4j.store;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConvertXSLTTest {

    private static final String LS = System.lineSeparator();

    @Test
    public void whenConvertFileThenNewFile() throws IOException {
        Path path = Paths.get(System.getProperty("java.io.tmpdir"), "xmlFileTest.xml");
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
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
        Files.writeString(path, xml);
        ConvertXSLT convertXSLT = new ConvertXSLT();
        File dest = new File(System.getProperty("java.io.tmpdir")
                + File.separator + "xslFileTest.xsl");
        convertXSLT.convert(path.toFile(), dest,
                new File(MagnitMain.class.getClassLoader().getResource("schema.xsl").getFile()));
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + LS
                + "<entries>" + LS
                + "    <entry field=\"1\"/>" + LS
                + "    <entry field=\"2\"/>" + LS
                + "    <entry field=\"3\"/>" + LS
                + "</entries>";
        String result = Files.readString(dest.toPath());
        assertThat(result, is(expect));
        Files.deleteIfExists(path);
    }
}