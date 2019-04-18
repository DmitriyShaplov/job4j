package ru.job4j.productstorage;

import ru.job4j.productstorage.products.Food;
import ru.job4j.productstorage.storages.Storage;

import java.util.LinkedHashSet;
import java.util.Set;

public class ControlQuality {

    private Set<Storage> storages = new LinkedHashSet<>();

    /**
     * Adds storage in set.
     */
    public void addStorage(Storage storage) {
        this.storages.add(storage);
    }

    /**
     * Distributes products to storage.
     * @param product product.
     */
    public void distribute(Food product) {
        for (Storage storage : storages) {
            if (storage.addProduct(product)) {
                break;
            }
        }
    }
}
