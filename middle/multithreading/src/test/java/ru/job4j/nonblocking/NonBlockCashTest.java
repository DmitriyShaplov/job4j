package ru.job4j.nonblocking;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;

public class NonBlockCashTest {

    @Test
    public void whenThrowUpdateWithWrongVersion() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        NonBlockCache cache = new NonBlockCache();
        Base model1 = new Base(1, "test");
        model1.increment();
        Base model2 = new Base(1, "test");
        Thread thread = new Thread(() -> {
            cache.add(model1);
            try {
                cache.update(model2);
            } catch (Exception e) {
                ex.set(e);
            }
        });
        thread.start();
        thread.join();
        Assert.assertThat(ex.get().getMessage(), is("Wrong version"));
    }
}