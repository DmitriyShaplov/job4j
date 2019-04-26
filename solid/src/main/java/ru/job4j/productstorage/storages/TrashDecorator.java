package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.Food;
import ru.job4j.productstorage.products.RFood;

public class TrashDecorator extends RepStorage {
    public TrashDecorator(Storage storage) {
        super(storage);
    }

    @Override
    public boolean addProduct(Food repFood) {
        if (!((RFood) repFood).isCanReproduct()) {
            return super.addProduct(repFood);
        }
        return false;
    }
}
