package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Storage {

    /**
     * Stored products.
     */
    protected List<Food> products = new ArrayList<>();

    /**
     * Adds product if it satisfies the conditions.
     */
    public abstract boolean addProduct(Food product);

    /**
     * Returns unmodifiable List of Food.
     */
    public List<Food> getProducts() {
        return Collections.unmodifiableList(this.products);
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }
}
