package ru.job4j.professions;

/**
 * @author shaplov
 * @since 20.12.2018
 */
public abstract class Profession {
    public String name;

    public String profession;

    public String getProfession() {
        return this.profession;
    }

    public String getName() {
        return this.name;
    }

}
