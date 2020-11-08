package ru.shaplov.job4j.patterns.factory.jukebox.player;

/**
 * Интерфейс музыкального плеера.
 */
public interface JukeboxPlayer {

    void play();

    void pause();

    void stop();

    void setVolume(double volume);
}
