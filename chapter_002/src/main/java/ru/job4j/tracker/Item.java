package ru.job4j.tracker;

import java.util.*;

/**
 * This class for request Objects.
 * @author shaplov
 * @since 23.12.2018
 */
public class Item {
    private String id;
    private String name;
    private String desc;
    private long created;
    private String[] comments;
    private int comCnt = 0;

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.created = System.currentTimeMillis();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCreated() {
        return this.created;
    }
}
