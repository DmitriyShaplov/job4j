package ru.job4j.streamapi;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shaplov
 * @since 03.03.2019
 */
public class Profile {

    private Address address;

    public Profile(Address address) {
        this.address = address;
    }

    public Profile() {
    }

    /**
     *
     * @param profiles List of Profiles
     * @return List of Addresses
     */
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(v -> v.address)
                .distinct()
                .sorted(Comparator.comparing(Address::getCity))
                .collect(Collectors.toList());
    }
}
