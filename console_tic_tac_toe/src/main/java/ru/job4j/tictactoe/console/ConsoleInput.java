package ru.job4j.tictactoe.console;

import ru.job4j.tictactoe.Input;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author shaplov
 * @since 25.04.2019
 */
public class ConsoleInput implements Input {

    private Scanner scanner;
    private PrintStream out;

    public ConsoleInput(InputStream in, PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    @Override
    public String validateInput(int size) {
        StringBuilder sb = new StringBuilder();
        String rstString = null;
        boolean valid = false;
        boolean rawInputted = false;
        do {
            if (!rawInputted) {
                this.out.printf("Enter row (1-%d): ", size);
                if (scanner.hasNextInt()) {
                    int scanInt = scanner.nextInt();
                    scanner.nextLine();
                    if (scanInt > 0 && scanInt <= size) {
                        sb.append(scanInt - 1);
                        sb.append(":");
                        rawInputted = true;
                    } else {
                        System.out.println("Wrong row!");
                        continue;
                    }
                }
            }
            if (rawInputted) {
                this.out.printf("Enter column (1-%d): ", size);
                if (scanner.hasNextInt()) {
                    int scanInt = scanner.nextInt();
                    scanner.nextLine();
                    if (scanInt > 0 && scanInt <= size) {
                        sb.append(scanInt - 1);
                        rstString = sb.toString();
                        valid = true;
                    } else {
                        this.out.println("Wrong column!");
                        continue;
                    }
                }
            }
            if (!valid) {
                if (scanner.hasNext()) {
                    String scanned = scanner.nextLine();
                    if ("EXIT".equals(scanned.toUpperCase())
                            || "START".equals(scanned.toUpperCase())
                            || "INIT".equals(scanned.toUpperCase())) {
                        rstString = scanned;
                        valid = true;
                    }
                }
            }
            if (!valid) {
                this.out.println("Invalid input.");
            }
        } while (!valid);
        return rstString;
    }

    @Override
    public String getInitCommand() {
        boolean valid = false;
        String commandString;
        do {
            commandString = this.scanner.nextLine();
            if ("SIZE".equals(commandString.toUpperCase())
            || "SIDE".equals(commandString.toUpperCase())
            || "START".equals(commandString.toUpperCase())
            || "EXIT".equals(commandString.toUpperCase())) {
                valid = true;
            } else {
                System.out.println("Wrong input, chose \"size\", \"side\" or \"start\" to start the game");
                this.out.println("\"Exit\" - to exit");
            }
        } while (!valid);
        return commandString;
    }

    @Override
    public int getNewSize() {
        this.out.print("Enter board size (2-100): ");
        boolean valid = false;
        int size = 0;
        do {
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                scanner.nextLine();
                if (size > 1 && size < 101) {
                    this.out.printf("Board size set to %d%n", size);
                    valid = true;
                } else {
                    this.out.println("Wrong size!");
                }
            } else if (scanner.hasNext()) {
                if ("EXIT".equals(scanner.nextLine().toUpperCase())) {
                    valid = true;
                    size = -1;
                }
            }
        } while (!valid);
        return size;
    }

    @Override
    public String getSide() {
        boolean valid = false;
        String str;
        this.out.println("Chose your side, enter X or O: ");
        do {
            str = scanner.nextLine();
            if ("X".equals(str.toUpperCase())) {
                this.out.println("Your side is X now.");
                valid = true;
            } else if ("O".equals(str.toUpperCase())) {
                this.out.println("Your side is O now.");
                valid = true;
            } else if ("EXIT".equals(str.toUpperCase())) {
                valid = true;
            } else {
                this.out.println("Wrong input, enter X or O /exit");
            }
        } while (!valid);
        return str;
    }
}
