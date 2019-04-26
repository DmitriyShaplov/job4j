package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.Food;
import ru.job4j.productstorage.products.RFood;

public class ColdWHDecorator extends RepStorage {
    public ColdWHDecorator(Storage storage) {
        super(storage);
    }

    @Override
    public boolean addProduct(Food repFood) {
        if (((RFood) repFood).isCanReproduct() && ((RFood) repFood).isVegetable()) {
            return super.addProduct(repFood);
        }
        return false;
    }
}