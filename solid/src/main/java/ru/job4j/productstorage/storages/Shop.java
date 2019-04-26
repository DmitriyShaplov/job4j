package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.Food;

public class Shop extends Storage {

    private int discount = 0;

    public Shop() {
    }

    public Shop(int discount) {
        this.discount = discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return this.discount;
    }

    /**
     * Adds product if it satisfies the conditions.
     * Also adds discount to product if it 75% and more expired.
     */
    @Override
    public boolean addProduct(Food product) {
        var rst = false;
        int lifePer = product.lifePercentage();
        if (lifePer >= 25 && lifePer <= 100) {
            if (lifePer >= 75) {
                product.setDiscount(this.discount);
            }
            this.products.add(product);
            rst = true;
        }
        return rst;
    }
}
