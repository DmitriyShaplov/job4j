package ru.job4j.productstorage;

import org.junit.Test;
import ru.job4j.productstorage.products.*;
import ru.job4j.productstorage.storages.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RepControlTest {

    @Test
    public void whenAddProductsWithDifferentDaysExpiredThenRightDistributed() {
        RepStorage trash = new TrashDecorator(new Trash());
        RepStorage oldWarehouse = new OldWHDecorator(new Warehouse());
        RepStorage newWarehouse = new NewWHDecorator(new WarehouseRep());
        RepStorage shop = new ShopDecorator(new Shop(25));
        RepStorage coldWH = new ColdWHDecorator(new Trash());
        ControlQuality repControl = new ControlQuality();
        repControl.addStorage(shop);
        repControl.addStorage(oldWarehouse);
        repControl.addStorage(trash);
        repControl.addStorage(newWarehouse);
        repControl.addStorage(coldWH);
        repControl.distribute(
                new RFood(
                new Fish("Mackerel",
                        LocalDate.now().minusDays(5),
                        LocalDate.now().minusDays(1),
                        32500)
                )
        );
        repControl.distribute(
                new RFood(
                new Fish("Herring",
                        LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(1),
                        22200)
                )
        );
        repControl.distribute(
                new RFood(
                new Meat("Chicken",
                        LocalDate.now().minusDays(1),
                        LocalDate.now().plusDays(8),
                        17000)
                )
        );
        repControl.distribute(
                new RFood(
                new MilkProducts("32 cows",
                        LocalDate.now().minusDays(5),
                        LocalDate.now().plusDays(10),
                        6055)
                )
        );
        repControl.distribute(
                new RFood(
                new Fruits("Apple",
                        LocalDate.now().minusDays(5),
                        LocalDate.now().minusDays(2),
                        9500), true, false
                )
        );
        repControl.distribute(
                new RFood(
                        new Vegatables("Tomato",
                                LocalDate.now().minusDays(5),
                                LocalDate.now().minusDays(2),
                                9500), true, true
                )
        );
        String shopList = String.join(":", shop.getProducts().stream().map(Food::getName).collect(Collectors.toList()));
        String warehouseList = String.join(":", newWarehouse.getProducts().stream().map(Food::getName).collect(Collectors.toList()));
        String trashList = String.join(":", trash.getProducts().stream().map(Food::getName).collect(Collectors.toList()));
        String coldList = String.join(":", coldWH.getProducts().stream().map(Food::getName).collect(Collectors.toList()));
        String expectShop = ("Herring:32 cows");
        String expectWH = ("Chicken:Apple");
        String expectTrash = ("Mackerel");
        String expectCold = ("Tomato");
        assertThat(shopList, is(expectShop));
        assertThat(warehouseList, is(expectWH));
        assertThat(trashList, is(expectTrash));
        assertThat(coldList, is(expectCold));
    }
}