package ru.job4j.pack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipFile;

/**
 * Test for Archiver.
 * Creating a temporary structure with come files
 * then make archive,
 * assert that needed files there,
 * then delete temp files.
 * @author shaplov
 * @since 22.03.2019
 * @version 1.00
 */
public class ArchiverTest {

    private Path root;
    private static final String FS = File.separator;

    @Before
    public void makeStructure() throws IOException {
        root = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "archTest");
        Files.createFile(Paths.get(root + FS + "test.jpg"));
        Files.createFile(Paths.get(root + FS + "test.png"));
        Files.createFile(Paths.get(root + FS + "test.xml"));
        Path dir = Files.createDirectory(Paths.get(root + FS + "testDir"));
        Files.createFile(Paths.get(dir + FS + "test1.jpg"));
        Files.createFile(Paths.get(dir + FS + "test1.png"));
        Files.createFile(Paths.get(dir + FS + "test1.xml"));
    }

    @After
    public void deleteStructure() throws IOException {
        Files.walk(root)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void whenCreateArchiveWithoutJpgFilesThenArchiveWithPngAndXml() throws IOException {
        String testRoot = root.toString();
        Archiver archiver = new Archiver(new String[]{"-d", testRoot, "-o", "test.zip", "-e", "jpg"});
        archiver.createPack();
        Set<String> result = new HashSet<>();
        String tempDir = System.getProperty("java.io.tmpdir");
        String strRoot = tempDir.lastIndexOf(FS) == (tempDir.length() - 1)
                ? tempDir.substring(0, tempDir.length() - 1) : tempDir;
        ZipFile zipFile = new ZipFile(strRoot + FS + "test.zip");
        var entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            result.add(entries.nextElement().getName());
        }
        File file = new File(strRoot + FS + "test.zip");
        file.delete();
        Set<String> expect = Set.of("test.png", "test.xml", "testDir" + FS + "test1.png", "testDir" + FS + "test1.xml");
        assertThat(result, is(expect));
    }
}