package ru.shaplov.job4j.patterns.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Scanner;

public class MainUI {

    private final Logger log = LogManager.getLogger(MainUI.class);

    private final Scanner scanner;
    private final TechnicalSupport support;

    public MainUI(InputStream inputStream, TechnicalSupport support) {
        this.scanner = new Scanner(inputStream);
        this.support = support;
    }

    public void start() {
        String line;
        line = getInputString();
        while (!"stop".equals(line)) {
            ProblemLevel problemLevel = ProblemLevel.of(line);
            support.call(problemLevel);
            line = getInputString();
        }
    }

    private String getInputString() {
        log.debug("Опишите проблему...");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        new MainUI(System.in, new TechnicalSupportProxy()).start();
    }
}
