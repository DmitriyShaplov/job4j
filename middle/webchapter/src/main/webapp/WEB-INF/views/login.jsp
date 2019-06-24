<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign in</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script>
        $(loadCountries());

        function validateCheck() {
            var result = true;
            if ($('#new-login').val() === "" || $('#new-password').val() === ""
                || $('#new-name').val() === "" || $('#new-email') === "") {
                result = false;
            }
            if (!result) {
                alert("All fields must be filled!");
            }
            return result;
        }

        function validateSign() {
            var result = true;
            if ($('#login').val() === "" || $('#password').val() === "") {
                result = false;
            }
            if (!result) {
                alert("Enter login and password");
            }
            return result;
        }

        $(
            (function(){
                $("#select-country").change(function(){
                    loadCities();
                    $("#city").show(1000);
                });
            })
        );

        function loadCities() {
            var country = $("#select-country").val();
            $.ajax("./cities", {
                type: "get",
                data: "country=" + country,
                dataType: "json"
            }).done(function (cities) {
                var result = "";
                result += "<option selected disabled>--- Choose city ---</option>";
                for (var i = 0; i < cities.length; ++i) {
                    result += "<option>" + cities[i].title + "</option>";
                }
                document.getElementById("select-city").innerHTML = result;
            });
        }

        function loadCountries() {
            $.ajax("./countries").done(function(countries) {
                var result = "";
                result += "<option selected disabled>--- Choose country ---</option>";
                for (var i = 0; i < countries.length; ++i) {
                    result += "<option>" + countries[i].title + "</option>";
                }
                document.getElementById("select-country").innerHTML = result;
            });
        }

    </script>
    <style>
        body {
            background-color: #f2fdff;
        }
        .top {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <ul class="nav nav-pills nav-justified">
        <li <c:if test="${error == null}"> class="active"</c:if>><a data-toggle="tab" href="#sign"><b>Sign in</b></a></li>
        <li <c:if test="${error != null}"> class="active"</c:if>><a data-toggle="tab" href="#check"><b>Check in</b></a></li>
    </ul>
    <div class="tab-content">
        <div id="sign"  class="tab-pane fade <c:if test="${error == null}">in active</c:if>">
            <form action="${pageContext.servletContext.contextPath}/signin" method="post">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input id="login" type="text" class="form-control" name="login" placeholder="Login">
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input id="password" type="password" class="form-control" name="password" placeholder="Password">
                </div>
                <input type="submit" class="btn btn-default top" onclick="return validateSign()">
            </form>
            <c:if test="${invalid != null}">
                <div class="alert alert-danger alert-dismissible fade in">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Danger!</strong> <c:out value="${invalid}"/>
                </div>
            </c:if>
        </div>
        <div id="check" class="tab-pane fade <c:if test="${error != null}">in active</c:if>">
            <form action="${pageContext.servletContext.contextPath}/create" method="post">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input id="new-login" type="text" class="form-control" name="login" placeholder="Login">
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input id="new-password" type="password" class="form-control" name="password" placeholder="Password">
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-info-sign"></i></span>
                    <input id="new-name" type="text" class="form-control" name="name" placeholder="Name">
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                    <input id="new-email" type="email" class="form-control" name="email" placeholder="Email">
                </div>
                <div id="country" class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-globe"></i></span>
                    <select class="form-control" id="select-country" name="country">
                    </select>
                </div>
                <div id="city" class="input-group" style="display: none">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                    <select class="form-control" id="select-city" name="city">
                    </select>
                </div>
                <input type="submit" class="btn btn-default top" onclick="return validateCheck()">
            </form>
            <c:if test="${error != null}">
                <div class="alert alert-danger alert-dismissible fade in">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Error!</strong> <c:out value="${error}"/>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
