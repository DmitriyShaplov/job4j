package ru.shaplov.job4j.patterns.electronicwaiter;

public abstract class AbstractOrder implements Order {

    private final String name;
    private final double cost;
    private boolean isReady;
    private User user;

    public AbstractOrder(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void setReady() {
        this.isReady = true;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    protected abstract String getPrefix();

    @Override
    public String getDescription() {
        return getPrefix() + name + ".";
    }

    @Override
    public Double getCost() {
        return cost;
    }
}


