package ru.job4j.createmenu;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 19.04.2019
 */
public class MenuUITest {

    private final String LN = System.lineSeparator();

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Test
    public void whenInitAndShowMenu() {
        MenuControl control = initMenu();
        ByteArrayInputStream in = new ByteArrayInputStream(String.join(LN,
                "show menu",
                "exit").getBytes()
        );
        MenuUI menu = new MenuUI(control, output, in);
        menu.start();
        String expect = String.join(LN,
                "Задача1. 1.",
                "---- Задача1.1. 1.1.",
                "-------- Задача1.1.1. 1.1.1.",
                "-------- Задача1.1.2. 1.1.2.",
                "---- Задача1.2. 1.2.") + LN;
        assertThat(out.toString(), is(expect));
    }

    @Test
    public void whenExecuteTask() {
        MenuControl control = initMenu();
        ByteArrayInputStream in = new ByteArrayInputStream(String.join(LN,
                "1.1.2.",
                "exit").getBytes()
        );
        MenuUI menu = new MenuUI(control, output, in);
        menu.start();
        String expect = "Задача1.1.2. 1.1.2. action executed" + LN;
        assertThat(out.toString(), is(expect));
    }

    /**
     * Fill menu with Menu elements for tests.
     * @return MenuControl.
     */
    private MenuControl initMenu() {
        MenuControl control = new MenuControl(output);
        MenuElement el1 = new MenuElement("Задача1.", output);
        MenuElement el11 = new MenuElement("Задача1.1.", output);
        el1.addChild(el11);
        MenuElement el111 = new MenuElement("Задача1.1.1.", output);
        MenuElement el112 = new MenuElement("Задача1.1.2.", output);
        el11.addChild(el111);
        el11.addChild(el112);
        MenuElement el12 = new MenuElement("Задача1.2.", output);
        el1.addChild(el12);
        control.addRoot(el1);
        return control;
    }
}