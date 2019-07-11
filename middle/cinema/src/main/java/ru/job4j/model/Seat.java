package ru.job4j.model;

import java.util.Objects;

/**
 * @author shaplov
 * @since 26.06.2019
 */
public class Seat implements Comparable<Seat> {

    private int row;
    private int place;
    private int price;
    private boolean sold;

    public Seat(int row, int place, int price, boolean sold) {
        this.row = row;
        this.place = place;
        this.price = price;
        this.sold = sold;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public int compareTo(Seat o) {
        int rs1 = Integer.compare(this.row, o.row);
        return rs1 != 0 ? rs1 : Integer.compare(this.place, o.place);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seat seat = (Seat) o;
        return row == seat.row
                && place == seat.place
                && price == seat.price
                && sold == seat.sold;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, place, price, sold);
    }
}
