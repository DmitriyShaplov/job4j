package ru.job4j.iotasks;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 21.03.2019
 */
public class SearchTest {

    private Path tempPath;

    /**
     * Delete all files in specified directory.
     */
    @After
    public void deleteTempDir() {
        deleteDir(tempPath.toFile());
    }

    private void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }

    @Test
    public void whenGetListOfFilesWithTxtOnlyThenListOf4TxtFiles() throws IOException {
        Path dir = Files.createTempDirectory(
                Paths.get(System.getProperty("java.io.tmpdir")), "dir");
        tempPath = dir;
        Files.createTempFile(
                dir, "testTXT", ".txt");
        Files.createTempFile(
                dir, "testPNG", ".png");
        Files.createTempFile(
                dir, "testJPG", ".jpg");
        Path dir1 = Files.createTempDirectory(
                dir, "dir1");
        Path dir2 = Files.createTempDirectory(
                dir, "dir2");
        Path dirInner = Files.createTempDirectory(
                dir1, "dirInner");
        Files.createTempFile(
                dir1, "testTXT1", ".txt");
        Files.createTempFile(
                dir1, "testPNG1", ".png");
        Files.createTempFile(
                dir1, "testJPG1", ".JPG");
        Files.createTempFile(
                dir2, "testTXT2", ".txt");
        Files.createTempFile(
                dir2, "testPNG2", ".png");
        Files.createTempFile(
                dir2, "testJPG2", ".JPG");
        Files.createTempFile(
                dirInner, "testTXT3", ".txt");
        Files.createTempFile(
                dirInner, "testPNG3", ".png");
        Files.createTempFile(
                dirInner, "testJPG3", ".JpG");
        Search search = new Search();
        List<File> files = search.filteredFiles(dir.toString(),
                List.of("jpg", "png"));
        boolean result = true;
        for (File file : files) {
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                result = false;
                break;
            }
        }
        assertTrue(result);
        assertThat(files.size(), is(4));
    }

    @Test
    public void whenGetListOfFilesWithTxtAndJpgThenListOf8TxtJpgFiles() throws IOException {
        Path dir = Files.createTempDirectory(
                Paths.get(System.getProperty("java.io.tmpdir")), "dir");
        tempPath = dir;
        Files.createTempFile(
                dir, "testTXT", ".txt");
        Files.createTempFile(
                dir, "testPNG", ".png");
        Files.createTempFile(
                dir, "testJPG", ".jpg");
        Path dir1 = Files.createTempDirectory(
                dir, "dir1");
        Path dir2 = Files.createTempDirectory(
                dir, "dir2");
        Path dirInner = Files.createTempDirectory(
                dir1, "dirInner");
        Files.createTempFile(
                dir1, "testTXT1", ".txt");
        Files.createTempFile(
                dir1, "testPNG1", ".png");
        Files.createTempFile(
                dir1, "testJPG1", ".JPG");
        Files.createTempFile(
                dir2, "testTXT2", ".txt");
        Files.createTempFile(
                dir2, "testPNG2", ".png");
        Files.createTempFile(
                dir2, "testJPG2", ".JPG");
        Files.createTempFile(
                dirInner, "testTXT3", ".txt");
        Files.createTempFile(
                dirInner, "testPNG3", ".png");
        Files.createTempFile(
                dirInner, "testJPG3", ".JpG");
        Search search = new Search();
        List<File> files = search.filteredFiles(dir.toString(),
                List.of("png"));
        boolean result = true;
        for (File file : files) {
            if (!file.getName().toLowerCase().endsWith(".txt")
            && !file.getName().toLowerCase().endsWith(".jpg")) {
                result = false;
                break;
            }
        }
        assertTrue(result);
        assertThat(files.size(), is(8));
    }
}