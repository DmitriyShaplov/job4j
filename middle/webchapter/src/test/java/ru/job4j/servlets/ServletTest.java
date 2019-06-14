package ru.job4j.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DbStore.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "javax.management.*", "org.w3c.dom.*"})
public class ServletTest {

    @Before
    public void init() {
        Store store = MemoryStore.getInstance();
        PowerMockito.mockStatic(DbStore.class);
        PowerMockito.when(DbStore.getInstance()).thenReturn(store);
    }

    @Test
    public void whenAddUserThenStoreIt() throws IOException, ServletException {
        Store store = MemoryStore.getInstance();
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("TEST1");
        when(req.getParameter("login")).thenReturn("Dmitriy");
        when(req.getParameter("email")).thenReturn("shaplovd@gmail.com");
        when(req.getParameter("password")).thenReturn("123456");
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn("");
        new UserCreateServlet().doPost(req, resp);
        User user = store.findByLogin(new User("", "Dmitriy"));
        assertThat(user.getName(), is("TEST1"));
    }

    @Test
    public void whenEditUserThenChangedLogin() throws IOException, ServletException {
        Store store = MemoryStore.getInstance();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        String id = store.add(new User("", "name", "login", "email", "password")).getId();
        when(req.getParameter("id")).thenReturn(id);
        when(req.getParameter("name")).thenReturn("TEST2");
        when(req.getParameter("login")).thenReturn("newLogin");
        when(req.getParameter("email")).thenReturn("shaplovd@gmail.com");
        when(req.getParameter("password")).thenReturn("123456");
        when(req.getRequestDispatcher("/WEB-INF/views/edit.jsp")).thenReturn(mock(RequestDispatcher.class));
        new UserUpdateServlet().doPost(req, resp);
        assertThat(store.findById(new User(id)).getLogin(), is("newLogin"));
    }

    @Test
    public void whenDeleteUserThenNull() throws IOException {
        Store store = MemoryStore.getInstance();
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        String id = store.add(new User("", "name1", "login1", "email1", "password1")).getId();
        when(req.getParameter("id")).thenReturn(id);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn("");
        new UsersServlet().doPost(req, resp);
        assertNull(store.findById(new User(id)));
    }
}