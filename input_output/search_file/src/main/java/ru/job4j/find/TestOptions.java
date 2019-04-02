package ru.job4j.find;

import org.apache.commons.cli.*;

public class TestOptions {

    private CommandLine commandLine;
    private boolean valid = true;

    public TestOptions(String[] args) {
        Options options = new Options();
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(new Option("f", "file", false, "Full file name"));
        optionGroup.addOption(new Option("m", "mask", false, "Mask"));
        optionGroup.addOption(new Option("r", "regexp", false, "RegExp"));
        options.addOptionGroup(optionGroup);
        options.addOption("d", "directory", true, "directory");
        options.addOption("n", "name", true, "name");
        options.addOption("o", "log directory", true, "log directory");
        CommandLineParser cmdLP = new DefaultParser();
        try {
            this.commandLine = cmdLP.parse(options, args);
        } catch (ParseException e) {
            this.valid = false;
            System.err.println("Parsiong failed. Reason: " + e.getMessage());
        }
    }

    public boolean isValid() {
        return this.valid;
    }

    public String directory() {
        if (commandLine.hasOption("d")) {
            return commandLine.getOptionValue("d");
        }
        return "";
    }

    public String name() {
        if (commandLine.hasOption("n")) {
            return commandLine.getOptionValue("n");
        }
        return "";
    }

    public NameType type() {
        NameType result;
        if (commandLine.hasOption("f")) {
            result = NameType.FULL_NAME;
        } else if (commandLine.hasOption("m")) {
            result = NameType.MASK;
        } else if (commandLine.hasOption("r")) {
            result = NameType.REGEXP;
        } else {
            result = null;
        }
        return result;
    }

    public String logDirectory() {
        if (commandLine.hasOption("o")) {
            return commandLine.getOptionValue("o");
        }
        return "";
    }
}
