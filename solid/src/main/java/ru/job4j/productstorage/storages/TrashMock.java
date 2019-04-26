package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.Food;

import java.util.List;

/**
 * Mock class for testing new method 'resort'.
 */
public class TrashMock extends Storage {

    private boolean canStore = true;
    protected Storage storage;

    public TrashMock(Storage storage) {
        this.storage = storage;
    }

    /**
     * Change behavior for test.
     */
    public void setCanStore(boolean canStore) {
        this.canStore = canStore;
    }

    @Override
    public List<Food> getProducts() {
        return storage.getProducts();
    }

    @Override
    public List<Food> pickUpAll() {
        return storage.pickUpAll();
    }

    @Override
    public int hashCode() {
        return storage.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return storage.equals(obj);
    }

    @Override
    public boolean addProduct(Food product) {
        if (this.canStore) {
            return storage.addProduct(product);
        }
        return false;
    }
}
