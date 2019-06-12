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
            <c:if test="${user.role != 'ADMIN' || role == 'ADMIN'}">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <c:if test="${priority > user.priority || curId == user.id}">
                    <td><form action="${pageContext.servletContext.contextPath}/edit">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="edit">
                    </form></td>
                    <td><form action="${pageContext.servletContext.contextPath}/" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="delete">
                    </form></td>
                </c:if>
                <c:if test="${role == 'ADMIN'}">
                    <td><form action="${pageContext.servletContext.contextPath}/role" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <select name="role">
                            <c:choose>
                                <c:when test="${user.priority == 3}">
                                    <option value="ADMIN" selected>Admin</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ADMIN">Admin</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${user.priority == 2}">
                                    <option value="MODER" selected>Moder</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="MODER">Moder</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${user.priority == 1}">
                                    <option value="USER" selected>User</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="USER">User</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <input type="submit">
                    </form></td>
                </c:if>
            </tr>
            </c:if>
        </c:forEach>
    </table>
    <br/>
    <form action="${pageContext.servletContext.contextPath}/signout" method="post">
        <input type="submit" value="Sign out">
    </form>
</body>
</html>
