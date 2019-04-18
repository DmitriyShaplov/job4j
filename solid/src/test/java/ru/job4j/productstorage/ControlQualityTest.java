package ru.job4j.productstorage;

import org.junit.Test;
import ru.job4j.productstorage.products.*;
import ru.job4j.productstorage.storages.Shop;
import ru.job4j.productstorage.storages.Storage;
import ru.job4j.productstorage.storages.Trash;
import ru.job4j.productstorage.storages.Warehouse;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenAddProduct80ExpiredThenPriceChanges() {
        Storage shop = new Shop(25);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStorage(shop);
        controlQuality.distribute(new Fish("Herring",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(1),
                22200));
        long price = shop.getProducts().get(0).getPrice();
        String priceStr = shop.getProducts().get(0).priceAsText();
        assertThat(price, is(16650L));
        assertThat(priceStr, is("166р. 50коп."));
    }

    @Test
    public void whenAddProductsWithDifferentDaysExpiredThenRightDistributed() {
        Storage trash = new Trash();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop(25);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStorage(shop);
        controlQuality.addStorage(warehouse);
        controlQuality.addStorage(trash);
        controlQuality.distribute(
                new Fish("Mackerel",
                        LocalDate.now().minusDays(5),
                        LocalDate.now().minusDays(1),
                        32500)
        );
        controlQuality.distribute(
                new Fish("Herring",
                        LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(1),
                        22200)
        );
        controlQuality.distribute(
                new Meat("Chicken",
                        LocalDate.now().minusDays(1),
                        LocalDate.now().plusDays(8),
                        17000)
        );
        controlQuality.distribute(
                new MilkProducts("32 cows",
                        LocalDate.now().minusDays(5),
                        LocalDate.now().plusDays(10),
                        6055)
        );
        controlQuality.distribute(
                new Fruits("Apple",
                        LocalDate.now().minusDays(5),
                        LocalDate.now().minusDays(2),
                        9500)
        );
        String shopList = String.join(":", shop.getProducts().stream().map(Food::getName).collect(Collectors.toList()));
        String warehouseList = String.join(":", warehouse.getProducts().stream().map(Food::getName).collect(Collectors.toList()));
        String trashList = String.join(":", trash.getProducts().stream().map(Food::getName).collect(Collectors.toList()));
        String expectShop = ("Herring:32 cows");
        String expectWH = ("Chicken");
        String expectTrash = ("Mackerel:Apple");
        assertThat(shopList, is(expectShop));
        assertThat(warehouseList, is(expectWH));
        assertThat(trashList, is(expectTrash));
    }
}