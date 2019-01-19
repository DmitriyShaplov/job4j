package ru.job4j.tracker;

import java.util.List;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public int ask(String question, List<Integer> range) {
         int key = Integer.valueOf(ask(question));
         boolean exists = false;
         for (int num : range) {
             if (key == num) {
                 exists = true;
                 break;
             }
         }
         if (exists) {
             return key;
         } else {
             throw new MenuOutException("Out of menu range.");
         }
    }
}
