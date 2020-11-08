package ru.shaplov.job4j.patterns.factory.jukebox.model;

import lombok.SneakyThrows;

import java.net.URL;

import ru.shaplov.job4j.patterns.factory.jukebox.player.BasicJukeboxPlayer;
import ru.shaplov.job4j.patterns.factory.jukebox.player.JukeboxPlayer;

/**
 * Сервис, который работает с basic проигрывателем.
 */
public class BasicJukeboxService extends JukeboxService {

    private BasicJukeboxPlayer player;

    @Override
    @SneakyThrows
    protected JukeboxPlayer createPlayer(URL url) {
        if (player == null) {
            player = new BasicJukeboxPlayer(url);
        } else {
            player.changeTrack(url);
        }
        return player;
    }
}
