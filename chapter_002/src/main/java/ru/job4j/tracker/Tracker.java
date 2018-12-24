package ru.job4j.tracker;

import java.util.*;

/**
 * Обертка над массивом заявок.
 * @author shaplov
 * @since 24.12.2018
 * @version 1.00
 */
public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Метод, реализующий добавление заявки
     * в хранилище
     * @param item новая заявка
     * @return заявка с id
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
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
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                this.items[index] = item;
                this.items[index].setId(id);
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
        for (int index = 0; index < this.items.length; index++) {
            if (this.items[index].getId().equals(id)) {
                if (index != items.length - 1) {
                    System.arraycopy(this.items, index + 1, this.items, index, --this.position - index);
                } else {
                    this.position--;
                }
                this.items[position] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * @return копия массива items без null элементов
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        System.arraycopy(this.items, 0, result, 0, this.position);
        return result;
    }

    /**
     * @param key нужное нам наименование заявок
     * @return копия массива с заявками с нужным именем
     */
    public Item[] findByName(String key) {
        int elmCount = 0;
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                elmCount++;
            }
        }
        Item[] result = new Item[elmCount];
        int pos = 0;
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                result[pos++] = item;
            }
        }
        return result;
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
