package ru.job4j.bank;

/**
 * Class for user's accounts.
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class Account {
    private double value;
    private String requisites;

    /**
     * Constructor
     * @param value amount of money
     * @param requisites requisites
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public String getRequisites() {
        return requisites;
    }

    public double getValue() {
        return value;
    }

    boolean transfer(Account destination, double amount) {
        boolean success = false;
        if (Double.compare(value, 0.0) >= 0 && Double.compare(amount, this.value) <= 0 && destination != null) {
            success = true;
            this.value -= amount;
            destination.value += amount;
        }
        return success;
    }
}
