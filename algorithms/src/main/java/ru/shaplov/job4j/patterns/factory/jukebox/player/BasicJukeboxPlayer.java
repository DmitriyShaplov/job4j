package ru.shaplov.job4j.patterns.factory.jukebox.player;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import lombok.SneakyThrows;

import java.net.URL;

public class BasicJukeboxPlayer implements JukeboxPlayer {

    private final BasicPlayer mediaPlayer;

    public BasicJukeboxPlayer(URL url) throws BasicPlayerException {
        mediaPlayer = new BasicPlayer();
        mediaPlayer.open(url);
    }

    @Override
    @SneakyThrows
    public void play() {
        if (mediaPlayer.getStatus() == BasicPlayer.PAUSED) {
            mediaPlayer.resume();
        } else {
            mediaPlayer.play();
        }

    }

    @Override
    @SneakyThrows
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    @SneakyThrows
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    @SneakyThrows
    public void setVolume(double volume) {
        mediaPlayer.setGain(volume);
    }

    @SneakyThrows
    public void changeTrack(URL url) {
        mediaPlayer.stop();
        mediaPlayer.open(url);
    }
}
