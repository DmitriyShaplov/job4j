package ru.job4j.iotasks;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Searching all files in specified directory.
 * @author shaplov
 * @since 21.03.2019
 */
public class Search {

    /**
     * Searches files in specified directory.
     * @param parent String directory
     * @return list of files
     */
    public List<File> files(String parent) {
        Queue<File> queue = new LinkedList<>();
        List<File> listFiles = new ArrayList<>();
        File file = new File(parent);
        if (file.exists()) {
            queue.offer(file);
            while (!queue.isEmpty()) {
                File curFile = queue.poll();
                if (curFile.isDirectory()) {
                    for (File f : curFile.listFiles()) {
                        queue.offer(f);
                    }
                } else {
                    listFiles.add(curFile);
                }
            }
        }
        return listFiles;
    }

    /**
     * Returns filtered list of files.
     * @param parent String directory
     * @param exts List of extensions
     * @return list of files (filtered)
     */
    public List<File> filteredFiles(String parent, List<String> exts) {
        return filter(files(parent), exts);
    }

    /**
     * Filters files in list.
     */
    public List<File> filter(List<File> list, List<String> exts) {
        if (exts.isEmpty()) {
            return list;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(?i:(^.*\\.");
        sb.append(String.join("$)|(^.*\\.", exts));
        sb.append("$))");
        final String regex = sb.toString();
        list.removeIf(f -> f.getName().matches(regex));
        return list;
    }
}
