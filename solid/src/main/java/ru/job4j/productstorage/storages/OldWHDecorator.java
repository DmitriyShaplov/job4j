package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.RepFoodWithVegFlag;

public class OldWHDecorator extends RepStorage {
    public OldWHDecorator(Storage storage) {
        super(storage);
    }

    @Override
    public boolean addProduct(RepFoodWithVegFlag repFood) {
        return false;
    }
}
