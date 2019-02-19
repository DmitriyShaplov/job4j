package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Обертка над массивом заявок.
 * @author shaplov
 * @since 24.12.2018
 * @version 1.00
 */
public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    /**
     * Метод, реализующий добавление заявки
     * в хранилище
     * @param item новая заявка
     * @return заявка с id
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки
     * текущее время + рандом
     * @return Уникальный ключ
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод заменяет заявку в массиве заявок по id.
     * @param id - id заявки
     * @param item новая заявка
     * @return успешность замены
     */
    public boolean replace(String id, Item item) {
        if (item == null) {
            return false;
        }
        for (int index = 0; index < items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                item.setId(id);
                this.items.set(index, item);
                return true;
            }
        }
        return false;
    }

    /**
     * удаляет элемент из массива
     * @param id - id удаляемого элемента
     * @return успешность удаления
     */
    public boolean delete(String id) {
        for (int index = 0; index < items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                this.items.remove(index);
                return true;
            }
        }
        return false;
    }

    /**
     * @return весь список заявок
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * @param key нужное нам наименование заявок
     * @return arrayList с нужными нам заявками
     */
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                list.add(item);
            }
        }
        return list;
    }

    /**
     * @param id необходимый id
     * @return Item
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
