package ru.job4j.pack;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for launch arguments.
 * @author shaplov
 * @since 22.03.2019
 */
public class Args {

    private String[] args;

    private String directory;
    private String output;
    private List<String> exclude;
    private boolean parsed = false;

    public Args(String[] args) {
        this.args = args;
    }

    /**
     * parsing all arguments to fill fields.
     */
    private void parse() {
        int d = -1;
        int e = -1;
        int o = -1;
        for (int i = 0; i < args.length; ++i) {
            if ("-d".equals(args[i])) {
                d = i;
            }
            if ("-e".equals(args[i])) {
                e = i;
            }
            if ("-o".equals(args[i])) {
                o = i;
            }
        }
        if (d != -1) {
            this.directory = (d + 1) < args.length ? args[d + 1] : null;
        }
        if (o != -1) {
            this.output = (o + 1) < args.length ? args[o + 1] : null;
        }
        if (e != -1) {
            this.exclude = new ArrayList<>();
            for (int j = e + 1; j != d && j != o && j < args.length; ++j) {
                this.exclude.add(args[j]);
            }
        }
        this.parsed = true;
    }

    public String directory() {
        if (!parsed) {
            parse();
        }
        return this.directory;
    }

    public List<String> exclude() {
        if (!parsed) {
            parse();
        }
        return this.exclude;
    }

    public String output() {
        if (!parsed) {
            parse();
        }
        return this.output;
    }
}
