package ru.job4j.pseudo;

/**
 * @author shaplov
 * @since 08.01.2019
 * @version $Id$
 */
public class Square implements Shape {
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("+++++\n");
        pic.append("+++++\n");
        pic.append("+++++\n");
        pic.append("+++++");
        return pic.toString();
    }
}
