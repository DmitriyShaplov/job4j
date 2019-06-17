<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
    <c:if test="${error != ''}">
        <div style="background-color:red">
            <c:out value="${error}"/>
        </div>
    </c:if>
    <form action="${pageContext.servletContext.contextPath}/edit" method="post">
        Id : <c:out value="${user.id}"></c:out><br>
        Name : <input type="text" name="name" value="${user.name}"><br>
        Login : <input type="text" name="login" value="${user.login}"><br>
        Password : <input type="password" name="password" value="${user.password}"><br>
        Email : <input type="text" name="email" value="${user.email}"><br>
        <input type="hidden" name="id" value="${user.id}">
        <input type="submit">
    </form>
    <br>
    <form action="${pageContext.servletContext.contextPath}/list">
        <input type="submit" value="User's list">
    </form>
</body>
</html>
