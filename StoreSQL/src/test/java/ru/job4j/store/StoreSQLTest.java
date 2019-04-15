package ru.job4j.store;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 12.04.2019
 */
public class StoreSQLTest {

    /**
     * Deletes temp files.
     */
    private void deleteStructure(Path root) throws IOException {
        Files.walk(root)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    private Path createTempPath(Config config) throws IOException {
        String url = config.get("url");
        String sPath = url.substring(url.indexOf("jdbc:sqlite:") + 12);
        Path path = Paths.get(sPath).getParent();
        Files.createDirectories(path);
        return path;
    }

    @Test
    public void checkConnection() throws Exception {
        Config config = new ConfigForTest();
        config.init();
        Path path = createTempPath(config);
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            assertTrue(storeSQL.init());
        }
        deleteStructure(path);
    }

    @Test
    public void whenGenerateRecordsThenGetListOfEntries() throws Exception {
        Config config = new ConfigForTest();
        config.init();
        Path path = createTempPath(config);
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.init();
            storeSQL.generate(3);
            List<Entry> result = storeSQL.load();
            List<Entry> expect = List.of(
                    new Entry(1),
                    new Entry(2),
                    new Entry(3)
            );
            assertThat(result, is(expect));
        }
        deleteStructure(path);
    }
}