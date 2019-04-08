package ru.job4j.store;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Class for converting XML file to
 * other format via XSTL scheme.
 * @author shaplov
 * @since 08.04.2019
 */
public class ConvertXSLT {

    /**
     * Convert XML file to another File format
     * using XSLT scheme.
     */
    public File convert(File source, File dest, File scheme) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return dest;
    }
}
