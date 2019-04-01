package ru.job4j.find;

import java.util.*;

/**
 * Class for parsing and validating arguments for FindFiles class.
 * @author shaplov
 * @since 01.04.2019
 */
public final class FindArgs {

    private String[] args;

    /**
     * Map with keys and values.
     */
    private Map<String, String> valueMap;
    private String errors;

    public FindArgs(String[] args) {
        this.args = args;
    }

    /**
     * Parse command line arguments.
     * @return this
     */
    public FindArgs parse() {
        this.valueMap = new HashMap<>(Map.of("-d", "", "-n", "", "-o", "", "type", ""));
        for (int index = 0; index < args.length; ++index) {
            if (valueMap.containsKey(args[index].toLowerCase()) && index + 1 < args.length
                    && !args[index + 1].startsWith("-")) {
                valueMap.put(args[index].toLowerCase(), args[index + 1]);
            }
            if (this.valueMap.get("type").isEmpty()) {
                switch (args[index].toLowerCase()) {
                    case "-f":
                        this.valueMap.put("type", NameType.FULL_NAME.toString());
                        break;
                    case "-m":
                        this.valueMap.put("type", NameType.MASK.toString());
                        break;
                    case "-r":
                        this.valueMap.put("type", NameType.REGEXP.toString());
                        break;
                    default:
                }
            }
        }
        return this;
    }

    /**
     * Validates result of parsing
     */
    public boolean validate() {
        List<String> list = new ArrayList<>();
        if (this.valueMap.get("-d").isEmpty()) {
            list.add("directory");
        }
        if (this.valueMap.get("-n").isEmpty()) {
            list.add("name");
        }
        if (this.valueMap.get("type").isEmpty()) {
            list.add("name type");
        }
        if (this.valueMap.get("-o").isEmpty()) {
            list.add("log directory");
        }
        boolean result = list.isEmpty();
        if (!result) {
            this.errors = String.format("Run failed. Invalid arguments.%n"
                    + "Please enter %s.", String.join(", ", list));
        }
        return result;
    }

    public String getErrors() {
        return this.errors;
    }

    public String directory() {
        return this.valueMap.get("-d");
    }

    public String name() {
        return this.valueMap.get("-n");
    }

    public NameType type() {
        return NameType.valueOf(this.valueMap.get("type"));
    }

    public String logDirectory() {
        return this.valueMap.get("-o");
    }
}
