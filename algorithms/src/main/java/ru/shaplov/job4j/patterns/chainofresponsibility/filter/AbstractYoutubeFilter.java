package ru.shaplov.job4j.patterns.chainofresponsibility.filter;

import ru.shaplov.job4j.patterns.chainofresponsibility.exception.AccessDeniedException;
import ru.shaplov.job4j.patterns.chainofresponsibility.model.User;

/**
 * Абстрактный класс для всех фильтров, реализующий базовую логику паттерна "цепочка обязанностей".
 */
public abstract class AbstractYoutubeFilter implements YoutubeFilter {

    private YoutubeFilter nextFilter;

    /**
     * Добавить в цепочку следующий фильтр.
     *
     * @param filter следующий фильтр цепочки
     * @return следующий фильтр
     */
    @Override
    public YoutubeFilter linkNext(YoutubeFilter filter) {
        this.nextFilter = filter;
        return filter;
    }

    /**
     * Вызывает следующий фильтр в цепочке, если есть.
     *
     * @param user объект проверки
     */
    protected void checkNext(User user) {
        if (nextFilter != null) {
            nextFilter.check(user);
        }
    }

    /**
     * Отказать пользователю.
     *
     * @param msg сообщение с ошибкой.
     */
    protected void deny(String msg) {
        throw new AccessDeniedException(msg);
    }
}
