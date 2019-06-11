<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User's list</title>
</head>
<body>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th>
                Id
            </th>
            <th>
                Name
            </th>
            <th>
                Login
            </th>
            <th>
                Email
            </th>
            <th>
                Create Date
            </th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td><form action="${pageContext.servletContext.contextPath}/edit">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="edit">
                </form></td>
                <td><form action="${pageContext.servletContext.contextPath}/" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="delete">
                </form></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <form action="${pageContext.servletContext.contextPath}/create">
        <input type="submit" value="Add user">
    </form>
</body>
</html>
