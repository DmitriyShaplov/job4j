package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple server.
 * @author shaplov
 * @since 26.03.2019
 */
public class Server {

    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    private Map<String, String> answers = new HashMap<>();

    private void fillAnswers() {
        String ln = System.lineSeparator();
        answers.put("hello", "Hello, dear friend, I'm oracle.");
        answers.put("what does oracle see?", "The Oracle sees all");
        answers.put("i want to know", "One ought to have known." + ln + "Perhaps one did know.");
        answers.put("what should i do?", "One should have read the signs.");
        answers.put("you know nothing!", "I promise nothing.");
        answers.put("now i know everything", "Was this the fortune you wanted?");
    }

    /**
     * Main server loop.
     */
    public void initServer() throws IOException {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))
        ) {
            if (this.answers.isEmpty()) {
                fillAnswers();
            }
            String ask;
            do {
                System.out.println("wait command...");
                ask = in.readLine();
                System.out.println(ask);
                if (!"exit".equalsIgnoreCase(ask)) {
                    out.println(this.answers.getOrDefault(ask.toLowerCase(), "Should've known better."));
                    out.println();
                }
            } while (!"exit".equalsIgnoreCase(ask));
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(5656).accept()) {
            new Server(socket).initServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
