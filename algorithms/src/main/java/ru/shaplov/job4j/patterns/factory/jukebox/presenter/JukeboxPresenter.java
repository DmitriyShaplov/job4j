package ru.shaplov.job4j.patterns.factory.jukebox.presenter;

import java.util.Map;

import ru.shaplov.job4j.patterns.factory.jukebox.SongInfo;
import ru.shaplov.job4j.patterns.factory.jukebox.model.BasicJukeboxService;
import ru.shaplov.job4j.patterns.factory.jukebox.model.JavafxJukeboxService;
import ru.shaplov.job4j.patterns.factory.jukebox.model.JukeboxService;

/**
 * Слой представления музыкальной коробки.
 */
public class JukeboxPresenter {

    private JukeboxService jukeboxService;

    public JukeboxPresenter(String playerName) {
        if ("javafx".equals(playerName)) {
            this.jukeboxService = new JavafxJukeboxService();
        } else if ("basic".equals(playerName)) {
            this.jukeboxService = new BasicJukeboxService();
        } else {
            throw new RuntimeException("Не выбрана реализация плеера.");
        }
    }

    public void play() {
        jukeboxService.play();
    }

    public void pause() {
        jukeboxService.pause();
    }

    public void stop() {
        jukeboxService.stop();
    }

    public void setVolume(double volume) {
        jukeboxService.setVolume(volume);
    }

    public void changeTrack(Integer trackNo) {
        jukeboxService.changeTrack(trackNo);
    }

    public Map<Integer, SongInfo> getTrackList() {
        return jukeboxService.getTrackList();
    }

    public SongInfo currentTrack() {
        return jukeboxService.currentTrack();
    }

    public void exit() {
        stop();
        jukeboxService.exit();
    }
}
