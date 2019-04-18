package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.RepFoodWithVegFlag;

public class ShopDecorator extends  RepStorage {
    public ShopDecorator(Storage storage) {
        super(storage);
    }
}
