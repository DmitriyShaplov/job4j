package ru.job4j.productstorage.products;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Food {

    private String name;

    private LocalDate expireDate;
    private LocalDate createDate;

    private long price;
    private int discount = 0;

    /**
     * Food constructor
     * @param name name of product.
     * @param createDate creation date.
     * @param expireDate expiration date.
     * @param price price in kopecks.
     */
    public Food(String name, LocalDate createDate, LocalDate expireDate, long price) {
        if (expireDate.isBefore(createDate)) {
            expireDate = createDate;
        }
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
    }

    public Food() {
    }

    /**
     * Sets discount on this product.
     * @param discount discount in percentage between 0 and 100.
     */
    public void setDiscount(int discount) {
        if (discount > 100) {
            this.discount = 100;
        } else if (discount < 0) {
            this.discount = 0;
        } else {
            this.discount = discount;
        }
    }

    /**
     * Returns percentage of used lifetime.
     */
    public int lifePercentage() {
        if (this.createDate.equals(this.expireDate)) {
            return 100;
        }
        return this.createDate.until(LocalDate.now()).getDays() * 100
                / this.createDate.until(this.expireDate).getDays();
    }

    public long getPrice() {
        return this.price * (100 - this.discount) / 100;
    }

    public int getDiscount() {
        return discount;
    }

    public String getName() {
        return name;
    }

    /**
     * Sets new price on this product.
     * @param price price on kopecks.
     */
    public void setPrice(long price) {
        this.price = price;
    }

    /**
     * Get price as text.
     */
    public String priceAsText() {
        return String.format("%dр. %dкоп.",
                price * (100 - this.discount) / 10000,
                price * (100 - this.discount) / 100 % 100);
    }

    public abstract ProductType type();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return price == food.price
                && discount == food.discount
                && Objects.equals(name, food.name)
                && Objects.equals(expireDate, food.expireDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expireDate, createDate, price, discount);
    }
}
