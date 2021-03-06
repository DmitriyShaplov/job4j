package ru.job4j.productstorage.products;

import java.time.LocalDate;

public class Vegatables extends Food {
    public Vegatables(String name, LocalDate createDate, LocalDate expireDate, long price) {
        super(name, createDate, expireDate, price);
    }

    @Override
    public ProductType type() {
        return ProductType.Vegetables;
    }
}
