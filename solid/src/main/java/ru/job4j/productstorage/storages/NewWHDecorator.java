package ru.job4j.productstorage.storages;

import ru.job4j.productstorage.products.Food;
import ru.job4j.productstorage.products.RFood;

public class NewWHDecorator extends RepStorage {
    public NewWHDecorator(Storage storage) {
            super(storage);
        }

        @Override
        public boolean addProduct(Food repFood) {
            if (repFood.lifePercentage() < 25) {
                return super.addProduct(repFood);
            }
            if (repFood.lifePercentage() > 100) {
                if (((RFood) repFood).isCanReproduct() && !((RFood) repFood).isVegetable()) {
                    return super.addProduct(repFood);
                }
            }
            return false;
        }
}
