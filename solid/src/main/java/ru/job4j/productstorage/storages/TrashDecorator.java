package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.RepFoodWithVegFlag;

public class TrashDecorator extends RepStorage {
    public TrashDecorator(Storage storage) {
        super(storage);
    }

    @Override
    public boolean addProduct(RepFoodWithVegFlag repFood) {
        if (!repFood.isCanReproduct()) {
            return super.addProduct(repFood);
        }
        return false;
    }
}
