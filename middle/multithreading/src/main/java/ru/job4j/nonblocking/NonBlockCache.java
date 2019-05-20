package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shaplov
 * @since 20.05.2019
 */
public class NonBlockCache {

    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * Adds value to map.
     * @param model Base model.
     * @return model.
     */
    public Base add(final Base model) {
        return cache.putIfAbsent(model.getId(), model);
    }

    /**
     * Updates value in map with new value.
     * @param model Base model.
     * @return model.
     */
    public Base update(final Base model) {
        return cache.computeIfPresent(model.getId(),
                (k, v) -> {
                    if (v.version() != model.version()) {
                        throw new OptimisticException("Wrong version");
                    }
                    model.increment();
                    return cache.put(k, model);
                });
    }

    /**
     * Deletes value from map.
     * @param model Base model.
     * @return success.
     */
    public boolean delete(final Base model) {
        return cache.remove(model.getId(), model);
    }
}
