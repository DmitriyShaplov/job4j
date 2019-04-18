package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.RepFoodWithVegFlag;

public class ColdWHDecorator extends RepStorage {
    public ColdWHDecorator(Storage storage) {
        super(storage);
    }

    @Override
    public boolean addProduct(RepFoodWithVegFlag repFood) {
        if (repFood.isCanReproduct() && repFood.isVegetable()) {
            return super.addProduct(repFood);
        }
        return false;
    }
}