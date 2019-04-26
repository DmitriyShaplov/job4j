package ru.job4j.productstorage;

import ru.job4j.productstorage.products.RepFoodWithVegFlag;
import ru.job4j.productstorage.storages.RepStorage;

import java.util.LinkedHashSet;
import java.util.Set;

public class RepControl {
    private Set<RepStorage> storages = new LinkedHashSet<>();

    /**
     * Adds storage in set.
     */
    public void addStorage(RepStorage storage) {
        this.storages.add(storage);
    }

    /**
     * Distributes products to .
     * @param product product.
     */
    public void distribute(RepFoodWithVegFlag product) {
        for (RepStorage storage : storages) {
            if (storage.addProduct(product)) {
                break;
            }
        }
    }
}
