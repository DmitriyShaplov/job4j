package ru.job4j.store;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SAXPars extends DefaultHandler {

    private final Consumer<String> output;

    private List<Integer> numbers;

    public SAXPars(Consumer<String> output) {
        this.output = output;
    }

    @Override
    public void startDocument() throws SAXException {
        numbers = new ArrayList<>();
        output.accept("Start parsing document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("entry".equals(qName)) {
            this.numbers.add(Integer.valueOf(attributes.getValue("field")));
        }
    }

    @Override
    public void endDocument() throws SAXException {
        output.accept("End of document");
        output.accept("Sum of fields: " + numbers.stream().mapToInt(Integer::intValue).sum());
    }

    /**
     * Print in console sum of all field in file.
     */
    public void printFieldSum(File file) {
        try (InputStream in = new FileInputStream(file)) {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(this);
            xmlReader.parse(new InputSource(in));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
