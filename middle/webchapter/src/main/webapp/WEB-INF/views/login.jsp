<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<c:if test="${invalid != ''}">
    <div style="background-color:red">
        <c:out value="${invalid}"/>
    </div>
</c:if>
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <table>
            <tr>
                <th colspan="2">Sign in</th>
            </tr>
            <tr>
                <td width="70">Login :</td>
                <td><input type="text" name="login"><br></td>
            </tr>
            <tr>
                <td>Password :</td>
                <td><input type="password" name="password"/><br/></td>
            </tr>
        </table>
        <input type="submit">
    </form>
<hr align="left" width="300" size="1"/>
<c:if test="${error != ''}">
    <div style="background-color:red">
        <c:out value="${error}"/>
    </div>
</c:if>
    <form action="${pageContext.servletContext.contextPath}/create" method="post">
        <table>
            <tr>
                <th colspan="2">Check in</th>
            </tr>
            <tr>
                <td width="70">Login :</td> <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Password :</td> <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Name :</td> <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Email :</td> <td><input type="text" name="email"></td>
            </tr>
        </table>
        <input type="submit">
    </form>
</body>
</html>
