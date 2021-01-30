package ru.shaplov.job4j.patterns.chainofresponsibility.filter;

import ru.shaplov.job4j.patterns.chainofresponsibility.model.User;

/**
 * Проверка пользователя на статус бана.
 */
public class BanCheckFilter extends AbstractYoutubeFilter {
    @Override
    public void check(User user) {
        if (user.isBanned()) {
            deny("Забанен");
        }
        checkNext(user);
    }
}
