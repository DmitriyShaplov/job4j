package ru.job4j.tracker;

import java.util.List;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public String ask(String question, List<Integer> range) {
        while (true) {
            System.out.print(question);
            String result = scanner.nextLine();
            for (Integer number : range) {
                if (number.equals(Integer.valueOf(result))) {
                    return result;
                }
            }
        }
    }
}
