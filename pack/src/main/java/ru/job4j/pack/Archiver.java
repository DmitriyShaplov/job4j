package ru.job4j.pack;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class - archiver.
 * @author shaplov
 * @since 22.03.2019
 */
public class Archiver {

    private Args arg;

    /**
     * Constructor.
     * Initializes Args class.
     */
    public Archiver(String[] args) {
        this.arg = new Args(args);
    }

    /**
     * Creates a zip pack in the specified directory.
     */
    public boolean createPack() {
        boolean result = true;
        if (!checkArgs()) {
            return false;
        }
        File rootDir = new File(arg.directory());
        if (!rootDir.exists()) {
            System.out.println("The specified directory does not exists.");
            return false;
        }
        try (FileOutputStream fout = new FileOutputStream(rootDir.getParent() + "/" + arg.output());
             ZipOutputStream zout = new ZipOutputStream(fout)) {
            zout.setMethod(ZipOutputStream.DEFLATED);
            zout.setLevel(9);
            StringBuilder sb = new StringBuilder();
            var listExc = arg.exclude();
            final boolean needReg = listExc.size() > 0;
            if (listExc.size() > 0) {
                sb.append("(?i:(^.*\\.");
                sb.append(String.join("$)|(^.*\\.", listExc));
                sb.append("$))");
            }
            final String regex = sb.toString();
            byte[] buffer = new byte[1024];
            Files.walk(rootDir.toPath())
                    .filter(p -> !needReg || !p.toString().matches(regex))
                    .forEach(p ->
                    {
                        if (!p.toFile().isDirectory()) {
                            try {
                                zout.putNextEntry(new ZipEntry((rootDir.toPath().relativize(p)).toString()));
                                try (InputStream in = new FileInputStream(p.toFile())) {
                                    int len;
                                    while ((len = in.read(buffer)) > 0) {
                                        zout.write(buffer, 0, len);
                                    }
                                }
                            } catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Checks cmd arguments.
     */
    private boolean checkArgs() {
        boolean result = true;
        if (arg.directory() == null || arg.directory().isEmpty()) {
            result = false;
            System.out.println("Please enter a directory");
        }
        if (arg.output() == null || arg.output().isEmpty()) {
            result = false;
            System.out.println("Please enter an output");
        }
        return result;
    }
}
