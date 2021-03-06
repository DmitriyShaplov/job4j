package ru.job4j.persistence;

import ru.job4j.model.Seat;
import ru.job4j.model.User;

import java.util.List;

/**
 * @author shaplov
 * @since 26.06.2019
 */
public interface Store {
    List<Seat> getSeats();
    Seat getSeat(Seat seat);
    boolean makePayment(Seat seat, User user);
}
