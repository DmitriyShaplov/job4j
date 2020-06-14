package ru.shaplov.job4j.patterns.factory.jukebox;

public class SongInfo {
    private final String title;
    private final String url;

    public SongInfo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
