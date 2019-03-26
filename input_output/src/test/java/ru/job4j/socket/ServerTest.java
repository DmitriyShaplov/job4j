package ru.job4j.socket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author shaplov
 * @since 26.03.2019
 */
public class ServerTest {

    private static final String LN = System.lineSeparator();

    @Test
    public void whenAskExitThenServerStopsAndNothing() throws IOException {
        testServer("exit", "");
    }

    @Test
    public void whenAskHelloThenBackGreatOracle() throws IOException {
        testServer(String.join(System.lineSeparator(),
                "hello",
                "exit"),
                String.format("Hello, dear friend, I'm oracle.%s%s", LN, LN));
    }

    @Test
    public void whenUnsupportedAskThenShouldVeKnowBetter() throws IOException {
        testServer(String.join(LN,
                "unsupported ask",
                "exit"),
                String.format("Should've known better.%s%s", LN, LN));
    }

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                        input.getBytes()
        );
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.initServer();
        assertThat(out.toString(), is(expected)
        );
    }
}