package ru.shaplov.job4j.patterns.chainofresponsibility.filter;

import ru.shaplov.job4j.patterns.chainofresponsibility.exception.AccessDeniedException;
import ru.shaplov.job4j.patterns.chainofresponsibility.model.User;

/**
 * Фильтр для цепочки проверок.
 */
public interface YoutubeFilter {

    /**
     * Выполнить проверку на доступ пользователя к ресурсу, исходя из его настроек.
     *
     * @param user пользователь ресурса
     * @throws AccessDeniedException возможное исключение, если
     *                               у пользователя нет доступа к этому контенту
     */
    void check(User user);

    /**
     * Линкует фильтры.
     *
     * @param youtubeFilter следующий фильтр
     * @return следующий фильтр
     */
    YoutubeFilter linkNext(YoutubeFilter youtubeFilter);
}
