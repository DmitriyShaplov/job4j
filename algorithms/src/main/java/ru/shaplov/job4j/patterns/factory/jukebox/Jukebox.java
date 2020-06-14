package ru.shaplov.job4j.patterns.factory.jukebox;

import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class Jukebox {

    private final Map<Integer, SongInfo> songInfoMap = new LinkedHashMap<>();
    private MediaPlayer curPlayer;
    private SongInfo curTrack;

    public Jukebox() {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("song.properties");
             InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(in), StandardCharsets.UTF_8)) {
            Properties properties = new Properties();
            properties.load(reader);
            int index = 1;
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                songInfoMap.put(index++, new SongInfo((String) k, (String) v));
            }
        } catch (IOException e) {
            throw new IllegalStateException("could not load properties");
        }
    }

    public void start() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
        showTrackList();
        showCurrentTrack();
        showHelp();
        new Thread(() -> {
            boolean running = true;
            Scanner scanner = new Scanner(System.in);
            while (running) {
                try {
                    String line = scanner.nextLine();
                    if ("play".equals(line)) {
                        if (curPlayer != null) {
                            curPlayer.play();
                        }
                    } else if ("pause".equals(line)) {
                        if (curPlayer != null) {
                            curPlayer.pause();
                        }
                    } else if ("stop".equals(line)) {
                        if (curPlayer != null) {
                            curPlayer.stop();
                        }
                        com.sun.javafx.application.PlatformImpl.tkExit();
                        running = false;
                    } else {
                        int index = Integer.parseInt(line);
                        if (index < 1 || index > songInfoMap.size()) {
                            throw new IllegalArgumentException("Wrong track number!");
                        }
                        curTrack = songInfoMap.get(index);
                        showCurrentTrack();
                        if (curPlayer != null) {
                            curPlayer.stop();
                        }
                        curPlayer = PlayerFactory.getPlayerForSong(curTrack.getUrl());
                        curPlayer.play();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }

    private void showHelp() {
        System.out.println("Enter track number to chose current track," +
                "\n \"play\" - to start playing " +
                "\n \"pause\" - to pause" +
                "\n \"stop\" - to stop end exit");

    }

    private void showCurrentTrack() {
        System.out.println("Current track - " + (curTrack == null ? "none" : curTrack.getTitle()));
    }

    private void showTrackList() {
        songInfoMap.forEach((k, v) -> System.out.println(k + " " + v.getTitle()));
    }

    public static void main(String[] args) {
        Jukebox jukebox = new Jukebox();
        jukebox.start();
    }
}
