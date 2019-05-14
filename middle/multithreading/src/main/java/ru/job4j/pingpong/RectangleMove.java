package ru.job4j.pingpong;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

/**
 * @author shaplov
 * @since 14.05.2019
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final Scene scene;

    public RectangleMove(Rectangle rect, Scene scene) {
        this.rect = rect;
        this.scene = scene;
    }

    @Override
    public void run() {
        double deltaX = 1.0;
        double deltaY = 1.5;
        while (true) {
            if (this.rect.getX() <= 0
            || this.rect.getX() >= this.scene.getWidth() - this.rect.getWidth()) {
                deltaX *= -1;
            }
            if (this.rect.getY() <= 0
                    || this.rect.getY() >= this.scene.getWidth() - this.rect.getHeight()) {
                deltaY *= -1;
            }
            this.rect.setX(this.rect.getX() + deltaX);
            this.rect.setY(this.rect.getY() + deltaY);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
