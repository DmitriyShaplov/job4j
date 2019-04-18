package ru.job4j.productstorage.storages;

public class ShopDecorator extends  RepStorage {
    public ShopDecorator(Storage storage) {
        super(storage);
    }
}
