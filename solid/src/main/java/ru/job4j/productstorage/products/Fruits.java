package ru.job4j.productstorage.products;

import java.time.LocalDate;

public class Fruits extends Food {
    public Fruits(String name, LocalDate createDate, LocalDate expireDate, long price) {
        super(name, createDate, expireDate, price);
    }

    @Override
    public ProductType type() {
        return ProductType.Fruits;
    }
}
