package ru.job4j.find;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shaplov
 * @since 01.04.2019
 */
public class FindFiles {

    private FindArgs args;

    public FindFiles(FindArgs args) {
        this.args = args;
    }

    /**
     * Find all files in specified directory
     * filtered by mask, name, or regexp.
     * Creates log file with results.
     */
    public void find() {
        if (!this.args.validate()) {
            System.out.println(this.args.getErrors());
            return;
        }
        Path dir = Paths.get(args.directory());
        if (!dir.toFile().exists()) {
            System.out.println("Specified directory doesn't exists.");
            return;
        }
        FileSystem fs = dir.getFileSystem();
        final PathMatcher matcher = NameType.MASK.equals(args.type())
                ? fs.getPathMatcher("glob:" + args.name()) : fs.getPathMatcher("regex:" + args.name());
        List<Path> list;
        try {
            list = Files.walk(dir).filter(p -> matcher.matches(p.getFileName())).map(Path::toAbsolutePath)
                    .collect(Collectors.toList());

            try (PrintWriter out = new PrintWriter(
                    Files.newBufferedWriter(Paths.get(args.logDirectory()), StandardCharsets.UTF_8), true)) {
                if (!list.isEmpty()) {
                    for (Path p : list) {
                        out.println(p);
                    }
                    System.out.printf("Log file created %s%n", Paths.get(args.logDirectory()).toAbsolutePath());
                } else {
                    System.out.println("There are no matching files in this directory.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main function
     * @param args -d -directory
     *             -n -name
     *             -f/-m/-r - filter file by full name, mask or regexp
     *             -o -log directory
     */
    public static void main(String[] args) {
        new FindFiles(new FindArgs(args).parse()).find();
    }
}
