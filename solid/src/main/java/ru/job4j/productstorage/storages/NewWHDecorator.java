package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.RepFoodWithVegFlag;

public class NewWHDecorator extends RepStorage {
    public NewWHDecorator(Storage storage) {
            super(storage);
        }

        @Override
        public boolean addProduct(RepFoodWithVegFlag repFood) {
            if (repFood.lifePercentage() < 25) {
                return super.addProduct(repFood);
            }
            if (repFood.lifePercentage() > 100) {
                if (repFood.isCanReproduct() && !repFood.isVegetable()) {
                    return super.addProduct(repFood);
                }
            }
            return false;
        }
}
