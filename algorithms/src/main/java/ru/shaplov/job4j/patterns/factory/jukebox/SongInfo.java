package ru.shaplov.job4j.patterns.factory.jukebox;

import java.net.URL;

/**
 * DTO для трека.
 */
public class SongInfo {
    private final String title;
    private final URL url;

    public SongInfo(String title, URL url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public URL getUrl() {
        return url;
    }
}
