package ru.job4j.productstorage.products;

import java.time.LocalDate;

/**
 * This class extends Food classes by addition field "canReproduct".
 * @author shaplov
 * @since 18.04.2019
 */
public class RepFoodWithVegFlag {

    protected Food food;
    private boolean canReproduct = false;
    private boolean isVegetable = false;

    public RepFoodWithVegFlag(Food food) {
        this.food = food;
    }

    public RepFoodWithVegFlag(Food food, boolean canReproduct) {
        this.food = food;
        this.canReproduct = canReproduct;
    }

    public RepFoodWithVegFlag(Food food, boolean canReproduct, boolean isVegetable) {
        this.food = food;
        this.canReproduct = canReproduct;
        this.isVegetable = isVegetable;
    }

    public boolean isVegetable() {
        return isVegetable;
    }

    public Food getFood() {
        return this.food;
    }

    public boolean isCanReproduct() {
        return canReproduct;
    }

    public void setCanReproduct(boolean canReproduct) {
        this.canReproduct = canReproduct;
    }

    public void setDiscount(int discount) {
        food.setDiscount(discount);
    }

    public int lifePercentage() {
        return food.lifePercentage();
    }

    public long getPrice() {
        return food.getPrice();
    }

    public int getDiscount() {
        return food.getDiscount();
    }

    public String getName() {
        return food.getName();
    }

    public void setPrice(long price) {
        food.setPrice(price);
    }

    public String priceAsText() {
        return food.priceAsText();
    }

    public ProductType type() {
        return food.type();
    }

    @Override
    public int hashCode() {
        return food.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return food.equals(obj);
    }
}
