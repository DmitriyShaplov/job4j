package ru.job4j.createmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class contains root menu elements
 * and actions on them.
 * @author shaplov
 * @since 19.04.2019
 */
public class MenuControl {

    private final Consumer<String> output;

    /**
     * root elements.
     */
    protected List<MenuElement> roots = new ArrayList<>();

    /**
     * Default constructor.
     */
    public MenuControl() {
        this.output = System.out::println;
    }

    /**
     * Constructor for tests.
     */
    public MenuControl(Consumer<String> output) {
        this.output = output;
    }

    /**
     * Adds root element.
     * @param el Menu element.
     */
    public void addRoot(MenuElement el) {
        this.roots.add(el);
    }

    /**
     * Show all menu items in tree view.
     */
    public void showMenu() {
        for (int index = 0; index < roots.size(); ++index) {
            String menuNumber = (index + 1) + ".";
            output.accept(roots.get(index).getName() + " " + menuNumber);
            roots.get(index).showElems(1, menuNumber);
        }
    }

    /**
     * Executes menu item by id.
     * @param id menu item.
     */
    public void executeTask(String id) {
        String[] ids = id.split("\\.");
        this.roots.get(Integer.valueOf(ids[0]) - 1).action(ids, 0);
    }
}
