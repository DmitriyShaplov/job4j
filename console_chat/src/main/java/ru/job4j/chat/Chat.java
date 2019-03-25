package ru.job4j.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class for talking with bot
 * taking his responses from .txt file
 * from random line.
 * @author shaplov
 * @since 24.03.2019
 * @version 1.00
 */
public class Chat {

    private String logPath;
    private InputStream in;

    /**
     * Constructor.
     * @param logPath path to log file
     * @param in InputStream
     */
    public Chat(String logPath, InputStream in) {
        this.logPath = logPath;
        this.in = in;
    }

    private boolean response = true;

    /**
     * Main loop to chat with bot.
     */
    public void talk() {
        if (this.getClass().getClassLoader().getResource("replicas.txt") == null) {
            System.out.println("There is no specified file in resources");
            return;
        }
        try (RandomAccessFile raf = new RandomAccessFile(
                this.getClass().getClassLoader().getResource("replicas.txt").getFile(), "r"
        );
             BufferedReader rin = new BufferedReader(new InputStreamReader(in));
             PrintWriter pwout = new PrintWriter(new OutputStreamWriter(
                     new FileOutputStream(logPath, true), StandardCharsets.UTF_8), true)
         ) {
            String myLine;
            String botAnswer;
            boolean lastBot = true;
            do {
                myLine = rin.readLine();
                if (lastBot) {
                    pwout.println("Me (" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                            .format(Calendar.getInstance().getTime()) + "):");
                    lastBot = false;
                }
                pwout.println(myLine);
                if (myLine.toLowerCase().equals("стоп")
                        || myLine.toLowerCase().equals("закончить")) {
                    this.response = false;
                }
                if (myLine.toLowerCase().equals("продолжить")) {
                    this.response = true;
                }
                if (this.response) {
                    botAnswer = getBotLine(raf);
                    System.out.println(botAnswer);
                    pwout.println("Bot (" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                            .format(Calendar.getInstance().getTime()) + "):");
                    pwout.println(botAnswer);
                    lastBot = true;
                }
            } while (!myLine.toLowerCase().equals("закончить"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Gets random line from text file.
     * @param raf RandomAccessFile
     * @return String phrase
     */
    private String getBotLine(RandomAccessFile raf) throws IOException {
        long pos = (long) (Math.random() * raf.length());
        raf.seek(pos);
        while (pos > 0 && raf.read() != '\n') {
            raf.seek(--pos);
        }
        String phrase = raf.readLine();
        return phrase != null ? new String(phrase.getBytes(StandardCharsets.ISO_8859_1)) : null;
    }

    /**
     * Start program.
     */
    public static void main(String[] args) {
        new Chat("console_chat\\log.txt", System.in).talk();
    }
}
