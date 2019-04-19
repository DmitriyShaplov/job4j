package ru.job4j.createmenu;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Class for init and start Menu.
 * @author shaplov
 * @since 19.04.2019
 */
public class MenuUI {

    private final Scanner scanner;
    private MenuControl control;

    private final Consumer<String> output;

    /**
     * Default constructor.
     * @param control MenuControl.
     */
    public MenuUI(MenuControl control) {
        this.control = control;
        this.output = System.out::println;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructor for tests.
     * @param control MenuControl
     * @param output output consumer.
     */
    public MenuUI(MenuControl control, Consumer<String> output, InputStream in) {
        this.control = control;
        this.output = output;
        this.scanner = new Scanner(in);
    }

    /**
     * Fill the menu with items.
     */
    public static MenuControl initMenuControl() {
        MenuControl menu = new MenuControl();
        MenuElement el1 = new MenuElement("Задача1.");
        MenuElement el11 = new MenuElement("Задача1.1.");
        el1.addChild(el11);
        MenuElement el111 = new MenuElement("Задача1.1.1.");
        MenuElement el112 = new MenuElement("Задача1.1.2.");
        el11.addChild(el111);
        el11.addChild(el112);
        MenuElement el12 = new MenuElement("Задача1.2");
        el1.addChild(el12);
        menu.addRoot(el1);
        return menu;
    }

    /**
     * Show menu programmatically.
     */
    public void showMenu() {
        this.control.showMenu();
    }

    /**
     * Main menu loop.
     */
    public void start() {
        String scanned = scanner.nextLine();
        while ( !"exit".equals(scanned)) {
            if ("show menu".equals(scanned)) {
                control.showMenu();
            } else if (scanned.matches("^(\\d?\\d\\.)+")) {
                try {
                    control.executeTask(scanned);
                } catch (Exception e) {
                    output.accept("Invalid menu number");
                }
            }
            scanned = scanner.nextLine();
        }
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        MenuControl control = MenuUI.initMenuControl();
        MenuUI menu = new MenuUI(control);
        menu.showMenu();
        menu.start();
    }
}
