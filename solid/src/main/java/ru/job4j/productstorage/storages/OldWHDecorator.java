package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.Food;

public class OldWHDecorator extends RepStorage {
    public OldWHDecorator(Storage storage) {
        super(storage);
    }

    @Override
    public boolean addProduct(Food repFood) {
        return false;
    }
}
