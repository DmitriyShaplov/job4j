package ru.shaplov.job4j.patterns.factory.jukebox.view;

import java.util.Scanner;

import ru.shaplov.job4j.patterns.factory.jukebox.presenter.JukeboxPresenter;

/**
 * Слой пользовательского интерфейса.
 */
public class JukeboxView {

    private final JukeboxPresenter jukeboxPresenter;

    public JukeboxView(JukeboxPresenter jukeboxPresenter) {
        this.jukeboxPresenter = jukeboxPresenter;
    }

    public void start() {
        showTrackList();
        showHelp();
        new Thread(() -> {
            boolean running = true;
            Scanner scanner = new Scanner(System.in);
            jukeboxPresenter.play();
            showCurrentTrack();
            while (running) {
                try {
                    String line = scanner.nextLine();
                    if ("play".equals(line)) {
                        jukeboxPresenter.play();
                    } else if ("pause".equals(line)) {
                        jukeboxPresenter.pause();
                    } else if ("stop".equals(line)) {
                        jukeboxPresenter.stop();
                    } else if ("exit".equals(line)) {
                        jukeboxPresenter.exit();
                        running = false;
                    } else if (line.matches("vol ((1)|(0\\.[1-9]))")) {
                        jukeboxPresenter.setVolume(Double.parseDouble(line.substring(4)));
                    } else {
                        int index = Integer.parseInt(line);
                        jukeboxPresenter.changeTrack(index);
                        showCurrentTrack();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }

    public void showHelp() {
        System.out.println("Enter track number to chose current track,"
                + "\n \"play\" - to start playing "
                + "\n \"pause\" - to pause"
                + "\n \"exit\" - to exit"
                + "\n \"vol [double value <= 1.0]\" - change volume"
                + "\n \"stop\" - to stop end exit");
    }

    public void showCurrentTrack() {
        System.out.println("Current track - " + jukeboxPresenter.currentTrack().getTitle());
    }

    public void showTrackList() {
        jukeboxPresenter.getTrackList().forEach((k, v) -> System.out.println(k + " " + v.getTitle()));
    }
}
