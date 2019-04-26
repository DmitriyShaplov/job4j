package ru.job4j.createmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class for menu element.
 * @author shaplov
 * @since 19.04.2019
 */
public class MenuElement {

    private final Consumer<String> output;

    /**
     * Menu element children.
     */
    protected List<MenuElement> childs = new ArrayList<>();

    private String name;

    /**
     * Default Constructor.
     * @param name name of menu element.
     */
    public MenuElement(String name) {
        this.output = System.out::println;
        this.name = name;
    }

    public MenuElement(String name, Consumer<String> output) {
        this.output = output;
        this.name = name;
    }

    /**
     * Adds child to this element.
     * @param el Menu element.
     */
    public void addChild(MenuElement el) {
        this.childs.add(el);
    }

    /**
     * Name getter.
     */
    public String getName() {
        return name;
    }

    /**
     * Shows elements in tree view.
     * @param level deep level.
     * @param preString parent id.
     */
    public void showElems(int level, String preString) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < level; ++i) {
            prefix.append("----");
        }
        for (int index = 0; index < childs.size(); ++index) {
            String menuNumber = preString + (index + 1) + ".";
            output.accept(
                    prefix + " " + this.childs.get(index).getName() + " " + menuNumber
            );
            this.childs.get(index).showElems(level + 1, menuNumber);
        }
    }

    /**
     * executes Menu item action.
     */
    public void action(String[] ids, int level) {
        if (ids.length == level + 1) {
            output.accept(this.name + " " + String.join(".", ids) + ". action executed");
        } else {
            this.childs.get(Integer.valueOf(ids[level + 1]) - 1).action(ids, level + 1);
        }
    }
}
