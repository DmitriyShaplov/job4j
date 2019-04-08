package ru.job4j.store;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SAXParsTest {

    private static final String LS = System.lineSeparator();

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Test
    public void whenSAXParsThenSumm() throws IOException {
        Path path = Paths.get(System.getProperty("java.io.tmpdir"), "xslSumTest.xsl");
        String xsl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + LS
                + "<entries>" + LS
                + "    <entry field=\"1\"/>" + LS
                + "    <entry field=\"2\"/>" + LS
                + "    <entry field=\"3\"/>" + LS
                + "</entries>";
        Files.writeString(path, xsl);
        SAXPars saxPars = new SAXPars(output);
        saxPars.printFieldSum(path.toFile());
        assertThat(new String(out.toByteArray()), is(
                new StringBuilder()
                        .append("Start parsing document")
                        .append(LS)
                        .append("End of document")
                        .append(LS)
                        .append("Sum of fields: 6")
                        .append(LS)
                        .toString()
        ));
        Files.deleteIfExists(path);
    }
}