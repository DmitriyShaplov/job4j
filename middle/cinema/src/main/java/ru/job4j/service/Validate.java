package ru.job4j.service;

import ru.job4j.model.Seat;
import ru.job4j.model.User;

import java.util.List;

/**
 * @author shaplov
 * @since 26.06.2019
 */
public interface Validate {
    List<Seat> getSeats();
    void makePayment(Seat seat, User user);
}
