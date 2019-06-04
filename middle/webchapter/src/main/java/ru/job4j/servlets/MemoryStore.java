package ru.job4j.servlets;

import net.jcip.annotations.ThreadSafe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Memory implementation of Store.
 * Singleton object.
 *
 * @author shaplov
 * @since 04.06.2019
 */
@ThreadSafe
public class MemoryStore implements Store {

    private final static MemoryStore INSTANCE = new MemoryStore();

    private final AtomicInteger idCounter = new AtomicInteger();

    /**
     * Concurrent Map for storing users.
     * K - user's Id.
     * V - User.
     */
    private final ConcurrentMap<String, User> users = new ConcurrentHashMap<>();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    /**
     * Appends Unique id and current date to user,
     * then puts user to map.
     * @param user user (name, login, email).
     * @return new Id.
     */
    @Override
    public String add(User user) {
        user.setId(String.valueOf(idCounter.incrementAndGet()));
        user.setCreateDate(LocalDate.now());
        users.put(user.getId(), user);
        return String.valueOf(user.getId());
    }

    /**
     * Replaces user in map by Id,
     * adds to this user current date.
     * @param user user model with id.
     * @return result.
     */
    @Override
    public boolean update(User user) {
        user.setCreateDate(LocalDate.now());
        return user.getId() != null && users.replace(user.getId(), user) != null;
    }

    /**
     * Deletes user.
     * @param user user model with id.
     * @return result.
     */
    @Override
    public boolean delete(User user) {
        return users.remove(user.getId()) != null;
    }

    /**
     * @return list of values.
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    /**
     * Returns user.
     * @param user user model with id.
     * @return user.
     */
    @Override
    public User findById(User user) {
        return users.get(user.getId());
    }
}
