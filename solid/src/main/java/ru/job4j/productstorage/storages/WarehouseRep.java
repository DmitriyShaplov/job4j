package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.Food;

public class WarehouseRep extends Storage {

    @Override
    public boolean addProduct(Food product) {
        var rst = false;
        if (product.lifePercentage() < 25
            || product.lifePercentage() > 100) {
            this.products.add(product);
            rst = true;
        }
        return rst;
    }
}
