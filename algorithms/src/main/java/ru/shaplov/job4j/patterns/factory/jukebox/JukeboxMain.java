package ru.shaplov.job4j.patterns.factory.jukebox;

import ru.shaplov.job4j.patterns.factory.jukebox.presenter.JukeboxPresenter;
import ru.shaplov.job4j.patterns.factory.jukebox.view.JukeboxView;

public class JukeboxMain {

    public static void main(String[] args) {
        JukeboxPresenter presenter = new JukeboxPresenter(args[0]);
        JukeboxView view = new JukeboxView(presenter);
        view.start();
    }
}
