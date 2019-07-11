package ru.job4j.service;

import ru.job4j.model.Seat;
import ru.job4j.model.User;
import ru.job4j.persistence.DbStore;
import ru.job4j.persistence.Store;

import java.util.List;

/**
 * @author shaplov
 * @since 26.06.2019
 */
public class ValidateService implements Validate {

    private final static Validate INSTANCE = new ValidateService();

    private final Store store = DbStore.getInstance();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Seat> getSeats() {
        return store.getSeats();
    }

    @Override
    public void makePayment(Seat seat, User user) {
        if (validateSeat(seat)) {
            store.makePayment(seat, user);
        } else {
            throw new IllegalStateException("Wrong price or this place is already sold");
        }
    }

    private boolean validateSeat(Seat seat) {
        var result = true;
        Seat dbSeat = store.getSeat(seat);
        if (dbSeat.getPrice() != seat.getPrice() || dbSeat.isSold()) {
            result = false;
        }
        return result;
    }
}
