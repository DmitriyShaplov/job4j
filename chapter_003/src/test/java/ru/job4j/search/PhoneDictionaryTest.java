package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author shaplov
 * @version $Id$
 * since 0.1
 */
public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(new Person("Dmitriy", "Shaplov", "6446448", "Odincovo"));
        var persons = phones.find("Dmitriy");
        assertThat(persons.iterator().next().getSurname(), is("Shaplov"));
    }

    @Test
    public void whenFindByPhonePartThenSizeIfThree() {
        var phones = new PhoneDictionary();
        phones.add(new Person("Dmitriy", "Shaplov", "6446448", "Odincovo"));
        phones.add(new Person("Dmitriy", "Shaplov", "6446448", "Odincovo"));
        phones.add(new Person("Dmitriy", "Shaplov", "6427459", "Odincovo"));
        phones.add(new Person("Dmitriy1", "Shaplov2", "6446448", "Odincovo1"));
        var persons = phones.find("644");
        assertThat(persons.size(), is(3));
    }
}
