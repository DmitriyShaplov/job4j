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

    public List<File> files(String parent, List<String> exts) {
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
        List<File> result = filter(listFiles, exts);
        return result;
    }

    /**
     * Filters files in list.
     */
    private List<File> filter(List<File> list, List<String> exts) {
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
