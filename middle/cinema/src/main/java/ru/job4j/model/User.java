package ru.job4j.model;

/**
 * @author shaplov
 * @since 26.06.2019
 */
public class User {

    private String username;
    private String phone;
    private Seat seat;

    public User(String username, String phone, Seat seat) {
        this.username = username;
        this.phone = phone;
        this.seat = seat;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
