package ru.job4j.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Simple client class.
 * @author shaplov
 * @since 26.03.2019
 */
public class Client {

    private Socket socket;
    private InputStream inputStream;

    public Client(Socket socket, InputStream inputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
    }

    /**
     * Main client loop.
     */
    public void initClient() throws IOException {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             Scanner console = new Scanner(this.inputStream)
        ) {
            String input;
            do {
                input = console.nextLine();
                String str;
                out.println(input);
                if (!"exit".equalsIgnoreCase(input)) {
                    do {
                        str = in.readLine();
                        if (!str.isEmpty()) {
                            System.out.println(str);
                        }
                    } while (!str.isEmpty());
                }
            } while (!"exit".equalsIgnoreCase(input));
            System.out.println("Server shut down");
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("localhost"), 5656)) {
            new Client(socket, System.in).initClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
