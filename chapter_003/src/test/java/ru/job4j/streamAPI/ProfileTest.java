package ru.job4j.streamAPI;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfileTest {

    @Test
    public void whenGetListOfAddresses() {
        Profile profile = new Profile();
        List<Profile> list = List.of(
                new Profile(new Address("test", "testStreet", 1, 2)),
                new Profile(new Address("test1", "testStreet1", 3, 4)),
                new Profile(new Address("test1", "testStreet1", 3, 4)),
                new Profile(new Address("test1", "testStreet1", 3, 4)),
                new Profile(new Address("test2", "testStreet2", 5, 6))
        );
        List<Address> addressList = profile.collect(list);
        String resString = addressList.stream()
                .map(Address::getCity)
                .collect(Collectors.joining());
        assertThat(addressList.size(), is(3));
        assertThat(resString, is("testtest1test2"));
    }
}
