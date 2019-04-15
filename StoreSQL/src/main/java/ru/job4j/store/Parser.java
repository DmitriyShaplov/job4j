package ru.job4j.store;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author shaplov
 * @since 15.04.2019
 */
public class Parser {

    public void start() throws IOException {
        Config config = new Config();
        config.init();
        String url = config.get("url");
        String sPath = url.substring(url.indexOf("jdbc:sqlite:") + 12);
        Path path = Paths.get(sPath).getParent();
        Files.createDirectories(path);
        File xml = new File("test.xml");
        File xsl = new File("test.xsl");
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.init();
        storeSQL.generate(1000000);
        StoreXML storeXML = new StoreXML(xml);
        storeXML.save(storeSQL.load());
        ConvertXSLT convertXSLT = new ConvertXSLT();
        convertXSLT.convert(xml, xsl, new File(MagnitMain.class.getClassLoader().getResource("schema.xsl").getFile()));
        SAXPars saxPars = new SAXPars(System.out::println);
        saxPars.printFieldSum(xsl);
    }
}
