package ru.job4j.generic;

/**
 * Abstract store.
 * @author shaplov
 * @since 07.03.2019
 * @param <T>
 */
public class AbstractStore<T extends Base> implements Store<T> {
    private SimpleList<T> values;

    public AbstractStore(int size) {
        this.values = new SimpleList<>(size);
    }

    @Override
    public void add(T model) {
        this.values.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (model == null || id == null) {
            return false;
        }
        boolean result = false;
        int index = 0;
        for (T value : this.values) {
            if (value != null && id.equals(value.getId())) {
                this.values.set(index, model);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        boolean result = false;
        int index = 0;
        for (T value : this.values) {
            if (value != null && id.equals(value.getId())) {
                this.values.remove(index);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T value : this.values) {
            if (value != null && id.equals(value.getId())) {
                result = value;
                break;
            }
        }
        return result;
    }
}
