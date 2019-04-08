package ru.job4j.store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

/**
 * Class for store List of Entry to XML.
 * @author shaplov
 * @since 08.04.2019
 */
public class StoreXML {

    private File file;

    public StoreXML(final File target) {
        this.file = target;
    }

    /**
     * Save list of Entry to XML file.
     */
    public void save(List<Entry> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(list), this.file);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    @XmlRootElement
    public static class Entries {
        private List<Entry> values;

        public Entries() {
        }

        public Entries(List<Entry> values) {
            this.values = values;
        }

        public List<Entry> getEntry() {
            return values;
        }

        public void setEntry(List<Entry> values) {
            this.values = values;
        }
    }
}
