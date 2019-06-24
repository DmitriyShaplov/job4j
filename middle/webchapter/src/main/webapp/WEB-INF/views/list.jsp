<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User's list</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#tUsers').DataTable({
                paging: false,
                searching: false,
                info: false
            });
        } );
    </script>
    <style>
        #tUsers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #40555e;
            color: white;
        }
        form {
            display: inline;
        }

        .btns {
            display: block;
            margin: 0 auto;
            width: 170px;
        }

        .btns2 {
            display: block;
            margin: 0 auto;
            width: 130px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>User's list</h2>
    <table id="tUsers" class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Country</th>
            <th>City</th>
            <th>Create Date</th>
            <th>Actions</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.country}"></c:out></td>
                <td><c:out value="${user.city}"></c:out></td>
                <td style="text-align: center"><c:out value="${user.createDate}"></c:out></td>
                <td><div class="btns2">
                <c:choose>
                    <c:when test="${priority > user.priority || curId == user.id}">
                        <form action="${pageContext.servletContext.contextPath}/edit">
                            <input type="hidden" name="id" value="${user.id}">
                            <button type="submit" class="btn btn-success">Edit</button>
                        </form>
                            <form action="${pageContext.servletContext.contextPath}/list" method="post">
                                <input type="hidden" name="id" value="${user.id}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.servletContext.contextPath}/edit">
                            <input type="hidden" name="id" value="${user.id}">
                            <button type="submit" class="btn btn-success" disabled = "disabled">Edit</button>
                        </form>
                            <form action="${pageContext.servletContext.contextPath}/list" method="post">
                                <input type="hidden" name="id" value="${user.id}">
                                <button type="submit" class="btn btn-danger" disabled = "disabled">Delete</button>
                            </form>
                    </c:otherwise>
                </c:choose>
                </div>
                </td>
                <c:choose>
                    <c:when test="${role == 'ADMIN'}">
                        <td><form class="form-inline btns" action="${pageContext.servletContext.contextPath}/role" method="post">
                            <input type="hidden" name="id" value="${user.id}">
                            <select class="form-control" name="role">
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
                            <button type="submit" class="btn">Change</button>
                        </form></td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <div class="well well-sm" style="padding: 5px; margin: 0 auto">
                                <c:choose>
                                    <c:when test="${user.priority == 3}">ADMIN</c:when>
                                    <c:when test="${user.priority == 2}">MODER</c:when>
                                    <c:when test="${user.priority == 1}">USER</c:when>
                                </c:choose>
                            </div>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="${pageContext.servletContext.contextPath}/signout" method="post">
        <input type="submit" class="btn btn-default" value="Sign out">
    </form>
</div>
</body>
</html>
