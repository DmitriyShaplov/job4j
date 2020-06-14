package ru.shaplov.job4j.patterns.factory.jukebox;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayerFactory {

    public static MediaPlayer getPlayerForSong(String url) {
        return new MediaPlayer(new Media(url));
    }
}
