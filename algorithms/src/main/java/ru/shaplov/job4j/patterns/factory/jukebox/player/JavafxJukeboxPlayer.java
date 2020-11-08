package ru.shaplov.job4j.patterns.factory.jukebox.player;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class JavafxJukeboxPlayer implements JukeboxPlayer {

    static {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
    }

    private MediaPlayer mediaPlayer;

    public JavafxJukeboxPlayer(URL url) {
        this.mediaPlayer = new MediaPlayer(new Media(url.toString()));
    }

    @Override
    public void play() {
        mediaPlayer.play();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }
}
