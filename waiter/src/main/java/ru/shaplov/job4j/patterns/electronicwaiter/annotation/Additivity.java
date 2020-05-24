package ru.shaplov.job4j.patterns.electronicwaiter.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Additivity {
    String value();
    double price();
}
