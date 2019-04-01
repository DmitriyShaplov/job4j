package ru.job4j.find;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


/**
 * @author shaplov
 * @since 01.04.2019
 */
public class FindFilesTest {

    @Test
    public void whenFindAllFilesByMaskThenLogFileWithAllFiles() throws IOException {
        testFindFiles("-d %s -n *.{jpg,txt} -m -o logT.txt", List.of(
                "fileJpg1", "fileJpg2", "fileTxt", "innerJpg1", "innerJpg2", "innerTxt"
        ));
    }

    @Test
    public void whenFindFilesByRegExpThenLogFileWithTestJpgFiles() throws IOException {
        testFindFiles("-d %s -n test.*\\.jpg -r -o logT.txt", List.of(
                "fileJpg1", "innerJpg1"
        ));
    }

    private void testFindFiles(String commands, List<String> stringList) throws IOException {
        Path sysPath = Paths.get(System.getProperty("java.io.tmpdir"));
        Path tempDir = Files.createTempDirectory(sysPath, "temp");
        Path tempDirInner = Files.createTempDirectory(tempDir, "inner");
        Path fileJpg1 = Files.createTempFile(tempDir, "test", ".jpg");
        Path fileJpg2 = Files.createTempFile(tempDir, "", ".jpg");
        Path fileTxt = Files.createTempFile(tempDir, "", ".txt");
        Path innerJpg1 = Files.createTempFile(tempDirInner, "test", ".jpg");
        Path innerJpg2 = Files.createTempFile(tempDirInner, "", ".jpg");
        Path innerTxt = Files.createTempFile(tempDirInner, "", ".txt");
        Map<String, String> map = Map.of("fileJpg1", fileJpg1.toString(),
                "fileJpg2", fileJpg2.toString(),
                "fileTxt", fileTxt.toString(),
                "innerJpg1", innerJpg1.toString(),
                "innerJpg2", innerJpg2.toString(),
                "innerTxt", innerTxt.toString());
        new FindFiles(new FindArgs(String.format(commands, tempDir).split(" ")).parse()).find();
        Set<String> result = new HashSet<>(Files.readAllLines(Paths.get("logT.txt"), StandardCharsets.UTF_8));
        Set<String> expected = new HashSet<>();
        for (String str : stringList) {
            expected.add(map.get(str));
        }
        assertThat(result, is(expected));
        Files.deleteIfExists(Paths.get("logT.txt"));
        Files.walk(tempDir).sorted(Comparator.reverseOrder()).forEach(path -> path.toFile().delete());
    }
}