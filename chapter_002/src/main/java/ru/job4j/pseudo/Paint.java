package ru.job4j.pseudo;

/**
 * @author shaplov
 * @since 08.01.2019
 * @version $Id$
 */
public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Square());
        paint.draw(new Triangle());
    }
}
