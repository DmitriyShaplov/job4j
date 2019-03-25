package ru.job4j.chat;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Test for Chat with Bot.
 * @author shaplov
 * @since 27.03.2019
 */
public class ChatTest {

    private static final String LN = System.lineSeparator();

    @Test
    public void whenChatWithBotThenExpectedLogFile() throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.join(
                        LN, "сообщение1", "стоп", "сообщение2", "продолжить", "сообщение3", "закончить").getBytes()
        );
        new Chat("logTEST.txt", in).talk();
        File file = new File("logTEST.txt");
        String result = Files.readString(Paths.get("logTEST.txt"), StandardCharsets.UTF_8);
        String expectedRegex = String.join(LN,
                "^Me \\(\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\):",
                "сообщение1",
                "Bot \\(\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\):",
                ".+",
                "Me \\(\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\):",
                "стоп",
                "сообщение2",
                "продолжить",
                "Bot \\(\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\):",
                ".+",
                "Me \\(\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\):",
                "сообщение3",
                "Bot \\(\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\):",
                ".+",
                "Me \\(\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\):",
                "закончить") + LN;
        Files.deleteIfExists(file.toPath());
        assertTrue(result.matches(expectedRegex));
    }
}