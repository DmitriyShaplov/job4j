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
        List<File> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean needReg = false;
        if (exts.size() > 0) {
            needReg = true;
            sb.append("(?i:(^.*\\.");
            sb.append(String.join("$)|(^.*\\.", exts));
            sb.append("$))");
        }
        final String regex = sb.toString();
        File file = new File(parent);
        if (file.exists()) {
            queue.offer(file);
            while (!queue.isEmpty()) {
                File curFile = queue.poll();
                if (curFile.isDirectory()) {
                    if (needReg) {
                        for (File f : curFile.listFiles(
                                (f, n) -> !n.matches(regex)
                        )) {
                            queue.offer(f);
                        }
                    } else {
                        for (File f : curFile.listFiles()) {
                            queue.offer(f);
                        }
                    }
                } else {
                    result.add(curFile);
                }
            }
        }
        return result;
    }
}
