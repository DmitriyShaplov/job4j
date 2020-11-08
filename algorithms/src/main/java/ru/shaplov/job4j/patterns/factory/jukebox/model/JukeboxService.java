package ru.shaplov.job4j.patterns.factory.jukebox.model;

import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ru.shaplov.job4j.patterns.factory.jukebox.SongInfo;
import ru.shaplov.job4j.patterns.factory.jukebox.player.JukeboxPlayer;

/**
 * Сервис слоя модели для работы с проигрывателями.
 */
public abstract class JukeboxService {

    private final Map<Integer, SongInfo> trackMap = new HashMap<>();

    private JukeboxPlayer currentPlayer;

    private SongInfo currentTrack;

    /**
     * Конструктор с необходимой инициализацией для работы сервиса.
     */
    @SneakyThrows
    public JukeboxService() {
        URL tracks = this.getClass().getClassLoader().getResource("tracks");
        assert tracks != null;
        File file = new File(tracks.toURI());
        int index = 1;
        File[] files = file.listFiles();
        assert files != null;
        if (files.length == 0) {
            throw new RuntimeException("Нет файлов для воспроизведения");
        }
        for (File listFile : files) {
            trackMap.put(index++, new SongInfo(listFile.getName(), listFile.toURI().toURL()));
        }
    }

    /**
     * Поменять трек.
     *
     * @param trackNo номер трека из списка
     */
    public void changeTrack(Integer trackNo) {
        if (trackNo < 1 || trackNo > trackMap.size()) {
            throw new RuntimeException("Номер трека выходит за границы");
        }
        if (currentPlayer != null) {
            stop();
        }
        SongInfo songInfo = trackMap.get(trackNo);
        currentPlayer = createPlayer(songInfo.getUrl());
        currentPlayer.play();
        currentTrack = songInfo;
    }

    protected abstract JukeboxPlayer createPlayer(URL url);

    public void exit() {
    }

    public Map<Integer, SongInfo> getTrackList() {
        return trackMap;
    }

    public SongInfo currentTrack() {
        return currentTrack;
    }

    public void play() {
        if (currentTrack == null) {
            changeTrack(1);
        }
        currentPlayer.play();
    }

    public void pause() {
        currentPlayer.pause();
    }

    public void stop() {
        currentPlayer.stop();
    }

    public void setVolume(double volume) {
        currentPlayer.setVolume(volume);
    }
}
