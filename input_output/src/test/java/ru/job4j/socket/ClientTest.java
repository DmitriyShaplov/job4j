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

public class ClientTest {

    private static final String LN = System.lineSeparator();

    @Test
    public void whenExitThenSendExitAndStop() throws IOException {
        testClient("exit", "", String.format("exit%s", LN));
    }

    @Test
    public void whenHelloEndExitThenSendHelloAndExit() throws IOException {
        testClient(String.join(LN, "hello", "exit"),
                String.format("Hello, dear friend, I'm oracle.%s%s", LN, LN),
                String.format("hello%sexit%s", LN, LN));
    }

    @Test
    public void whenUnsupportedRequestEndExitThenSendUnsupportedRequestAndExit() throws IOException {
        testClient(String.join(LN, "unsupported request", "exit"),
                String.format("Should've known better.%s%s", LN, LN),
                String.format("unsupported request%sexit%s", LN, LN));
    }

    private void testClient(String consoleInput, String serverInput, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream console = new ByteArrayInputStream(
                consoleInput.getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream(
                serverInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        new Client(socket, console).initClient();
        assertThat(out.toString(), is(expected));
    }
}