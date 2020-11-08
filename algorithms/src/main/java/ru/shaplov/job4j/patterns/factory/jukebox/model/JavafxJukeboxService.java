package ru.shaplov.job4j.patterns.factory.jukebox.model;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ru.shaplov.job4j.patterns.factory.jukebox.player.JavafxJukeboxPlayer;
import ru.shaplov.job4j.patterns.factory.jukebox.player.JukeboxPlayer;

/**
 * Сервис, который работает с javafx проигрывателем.
 */
public class JavafxJukeboxService extends JukeboxService {

    private final Map<URL, JavafxJukeboxPlayer> playerMap = new HashMap<>();

    @Override
    protected JukeboxPlayer createPlayer(URL url) {
        if (playerMap.containsKey(url)) {
            return playerMap.get(url);
        } else {
            JavafxJukeboxPlayer player = new JavafxJukeboxPlayer(url);
            playerMap.put(url, player);
            return player;
        }
    }

    @Override
    public void exit() {
        com.sun.javafx.application.PlatformImpl.tkExit();
    }
}
