package ru.shaplov.job4j.patterns.chainofresponsibility.filter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.shaplov.job4j.patterns.chainofresponsibility.exception.AccessDeniedException;
import ru.shaplov.job4j.patterns.chainofresponsibility.model.User;

public class YoutubeFilterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final YoutubeFilter filterChain = buildFilterChain();

    private YoutubeFilter buildFilterChain() {
        YoutubeFilter filter = new AdminCheckFilter();
        filter.linkNext(new BanCheckFilter())
                .linkNext(new AgeYoutubeFilter(16));
        return filter;
    }

    private void testFilterChain(boolean isAdmin, boolean isBanned, int age) {
        User user = User.builder()
                .isAdmin(isAdmin).isBanned(isBanned).age(age).build();
        filterChain.check(user);
    }

    @Test
    public void whenAdminThenPassed() {
        testFilterChain(true, true, 5);
    }

    @Test
    public void whenAllChecksPassed() {
        testFilterChain(false, false, 18);
    }

    @Test
    public void whenBannedThenException() {
        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("Забанен");

        testFilterChain(false, true, 20);
    }

    @Test
    public void whenLowAgeThenException() {
        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("Не проходит по возрасту");

        testFilterChain(false, false, 5);
    }
}