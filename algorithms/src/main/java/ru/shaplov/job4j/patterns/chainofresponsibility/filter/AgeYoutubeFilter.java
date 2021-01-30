package ru.shaplov.job4j.patterns.chainofresponsibility.filter;

import ru.shaplov.job4j.patterns.chainofresponsibility.model.User;

/**
 * Возврастной фильтр.
 */
public class AgeYoutubeFilter extends AbstractYoutubeFilter {

    private final int permittedAge;

    public AgeYoutubeFilter(int permittedAge) {
        this.permittedAge = permittedAge;
    }

    @Override
    public void check(User user) {
        if (user.getAge() < permittedAge) {
            deny("Не проходит по возрасту");
        }
        checkNext(user);
    }
}
