<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>
    <c:if test = "${error}">
        <span style="color: red; ">Error, such login is already exists!</span><br>
    </c:if>
    <form action="${pageContext.servletContext.contextPath}/create" method="post">
        Name : <input type="text" name="name"><br>
        Login : <input type="text" name="login"><br>
        Email : <input type="text" name="email"><br>
        <input type="submit">
    </form>
    <br>
    <form action="${pageContext.servletContext.contextPath}/">
        <input type="submit" value="User's list">
    </form>
</body>
</html>
