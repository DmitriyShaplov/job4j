<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>
    <% if ("repeat".equals(request.getParameter("error"))) { %>
    <span style="color: red; ">Error, such login is already exists!</span><br>
    <% } %>
    <form action="<%=request.getContextPath()%>/create" method="post">
        Name : <input type="text" name="name"><br>
        Login : <input type="text" name="login"><br>
        Email : <input type="text" name="email"><br>
        <input type="submit">
    </form>
    <br>
    <form action="<%=request.getContextPath()%>/list.jsp">
        <input type="submit" value="User's list">
    </form>
</body>
</html>
