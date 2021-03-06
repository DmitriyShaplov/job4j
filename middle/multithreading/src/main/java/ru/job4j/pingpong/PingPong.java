package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author shaplov
 * @since 14.05.2019
 */
public class PingPong extends Application {

    private static final String JOB4J = "Пинг- понг www.job4j.ru";

    @Override
    public void start(Stage stage) throws Exception {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Scene scene = new Scene(group, limitX, limitY);
        Thread moveThread = new Thread(new RectangleMove(rect, scene));
        moveThread.start();
        stage.setScene(scene);
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.setOnCloseRequest(
                windowEvent -> moveThread.interrupt()
        );
        stage.show();
    }
}
