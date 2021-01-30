package ru.shaplov.job4j.patterns.chainofresponsibility.filter;

import ru.shaplov.job4j.patterns.chainofresponsibility.model.User;

/**
 * Класс проверки на админа.
 */
public class AdminCheckFilter extends AbstractYoutubeFilter {
    @Override
    public void check(User user) {
        if (!user.isAdmin()) {
            checkNext(user);
        }
    }
}
