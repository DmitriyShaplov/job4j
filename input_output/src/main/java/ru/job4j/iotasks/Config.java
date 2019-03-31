package ru.job4j.iotasks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shaplov
 * @since 31.03.2019
 */
public class Config {

    private Path path;
    private final static String LN = System.lineSeparator();
    private boolean reliableData = false;
    private Map<String, String> stringMap = new HashMap<>();

    /**
     * Constructor.
     * @param path path to config file
     *             with extension or not
     */
    public Config(String path) {
        if (path.toLowerCase().endsWith(".properties")) {
            this.path = FileSystems.getDefault().getPath(path);
        } else {
            this.path = FileSystems.getDefault().getPath(path + ".properties");
        }
    }

    /**
     * fill map from file.
     */
    private void fillMap() throws IOException {
        if (!this.path.toFile().exists()) {
            this.reliableData = true;
            return;
        }
        try (BufferedReader in = Files.newBufferedReader(this.path, StandardCharsets.UTF_8)) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] pair = str.split("=");
                if (pair.length == 2) {
                    stringMap.put(pair[0], pair[1]);
                }
            }
        }
        this.reliableData = true;
    }

    /**
     * Rewrites file from map.
     */
    private void rewriteFile() throws IOException {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(this.path.toFile()), StandardCharsets.UTF_8), true)
        ) {
            for (Map.Entry<String, String> entry : this.stringMap.entrySet()) {
                out.println(entry.getKey() + "=" + entry.getValue());
            }
        }
    }

    /**
     * Gets value by key.
     * @param key key
     * @return value
     * @throws IOException e
     */
    public String get(String key) throws IOException {
        if (!this.reliableData) {
            fillMap();
        }
        return this.stringMap.get(key);
    }

    /**
     * Put pair key + value to this.path file
     * @param key key
     * @param value value
     * @throws IOException e
     */
    public void put(String key, String value) throws IOException {
        if (!this.reliableData) {
            fillMap();
        }
        if (this.stringMap.containsKey(key)) {
            this.stringMap.put(key, value);
            rewriteFile();
        } else {
            this.stringMap.put(key, value);
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(this.path.toFile(), true), StandardCharsets.UTF_8), true)
            ) {
                out.println(key + "=" + value);
            }
        }
    }
}
