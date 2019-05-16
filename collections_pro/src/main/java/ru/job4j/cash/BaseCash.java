package ru.job4j.cash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Cash class with values on SoftReferences.
 * @author shaplov
 * @since 01.05.2019
 */
public abstract class BaseCash implements Cash {

    private static final Logger LOG = LogManager.getLogger(StoredCash.class);

    private Map<String, SoftReference<String>> cash = new HashMap<>();

    @Override
    public String getCash(String key) {
        String res = null;
        if (cash.containsKey(key)) {
            res = cash.get(key).get();
        }
        if (res == null) {
            try {
                res = loadCash(key);
                addToCash(key, res);
                return res;
            } catch (Exception e) {
                LOG.error("Could not load value from file.", e);
            }
        }
        return res;
    }

    /**
     * Add  soft values in cash-map.
     * @param key string.
     * @param value string.
     */
    private void addToCash(String key, String value) {
        cash.put(key, new SoftReference<>(value));
    }

    /**
     * For load key-values.
     */
    abstract protected String loadCash(String key) throws Exception;
}
