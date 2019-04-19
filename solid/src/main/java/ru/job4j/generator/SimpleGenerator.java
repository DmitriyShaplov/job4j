package ru.job4j.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple realization of Generator.
 * @author shaplov
 * @since 19.04.2019
 */
public class SimpleGenerator implements Generator {

    private final static Pattern PATTERN = Pattern.compile("\\$\\{\\w+}");

    @Override
    public String generate(String template, Map<String, String> map) {
        Map<String, String> keyValue = new HashMap<>(map);
        Matcher matcher = PATTERN.matcher(template);
        while (matcher.find()) {
            String key = template.substring(matcher.start() + 2, matcher.end() - 1);
            if (!keyValue.containsKey(key)) {
                throw new IllegalStateException("No key in map");
            }
            template = template.replaceAll(String.format("\\$\\{%s}", key), keyValue.get(key));
            keyValue.remove(key);
            matcher = PATTERN.matcher(template);
        }
        if (!keyValue.isEmpty()) {
            throw new IllegalStateException("There is extra keys in map");
        }
        return template;
    }
}
