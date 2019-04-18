package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.Food;
import ru.job4j.productstorage.products.RepFoodWithVegFlag;

import java.util.List;

/**
 * @author shaplov
 * @since 18.04.2019
 */
public abstract class RepStorage {

    /**
     * Old storage.
     */
    protected Storage storage;

    public RepStorage(Storage storage) {
        this.storage = storage;
    }

    public boolean addProduct(RepFoodWithVegFlag repFood) {
        return storage.addProduct(repFood.getFood());
    }

    public List<Food> getProducts() {
        return storage.getProducts();
    }

    @Override
    public int hashCode() {
        return storage.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return storage.equals(obj);
    }
}
