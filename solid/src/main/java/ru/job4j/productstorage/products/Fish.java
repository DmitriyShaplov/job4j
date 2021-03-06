package ru.job4j.productstorage.products;

import java.time.LocalDate;

public class Fish extends Food {
    public Fish(String name, LocalDate createDate, LocalDate expireDate, long price) {
        super(name, createDate, expireDate, price);
    }

    @Override
    public ProductType type() {
        return ProductType.Fish;
    }
}
