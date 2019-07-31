<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit user</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = true;
            if ($('#name').val() === "" || $('#login').val() === ""
                || $('#password').val() === "" || $('#email').val() === "") {
                result = false;
            }
            if (!result) {
                alert("All fields must be filled");
            }
            return result;
        }

        $(loadCountries());

        function loadCountries() {
            $.ajax("./countries").done(function(countries) {
                var result = "";
                result += "<option selected disabled>--- Choose country ---</option>";
                for (var i = 0; i < countries.length; ++i) {
                    result += "<option>" + countries[i] + "</option>";
                }
                document.getElementById("country").innerHTML = result;
                if ("${user.country}" !== "") {
                    $("#country").val("${user.country}");
                    loadCities();
                } else {
                    $("#country").val();
                    $("#div-city").hide();
                }
            });
        }

        function loadCities() {
            var country = $("#country").val();
            $.ajax("./cities", {
                type: "get",
                data: "country=" + country,
                dataType: "json"
            }).done(function (cities) {
                var result = "";
                result += "<option selected disabled>--- Choose city ---</option>";
                for (var i = 0; i < cities.length; ++i) {
                    result += "<option>" + cities[i] + "</option>";
                }
                document.getElementById("city").innerHTML = result;
                var city = $('#city');
                $("#div-city").show(1000);
                if ('${user.city}' !== "" && city.find("option:contains('${user.city}')").length){
                    city.val("${user.city}");
                } else {
                    city.val();
                }
            });
        }

        $(
            (function(){
                $("#country").change(function(){
                    loadCities();
                });
            })
        );
    </script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h2>User edit page with ID <c:out value="${user.id}"></c:out></h2>
    </div>
    <div>
        <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/edit" method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Name:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name" value="${user.name}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="login">Login:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="login" name="login" value="${user.login}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Password:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="password" name="password" value="${user.password}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="email" name="email" value="${user.email}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="country">Country:</label>
                <div class="col-sm-10">
                    <select class="form-control" id="country" name="country">
                    </select>
                </div>
            </div>
            <div class="form-group" id="div-city">
                <label class="control-label col-sm-2" for="city">City:</label>
                <div class="col-sm-10">
                    <select class="form-control" id="city" name="city">
                    </select>
                </div>
            </div>
            <input type="hidden" name="id" value="${user.id}">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-default" value="Confirm changes" onclick="return validate()">
                    <a href="${pageContext.servletContext.contextPath}/list"
                       role="button" class="btn btn-default" style="float: right">
                        Back to User's list</a>
                </div>
            </div>
        </form>
    </div>
    <c:if test="${error != null}">
        <div class="alert alert-danger alert-dismissible fade in">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error!</strong> <c:out value="${error}"/>
        </div>
    </c:if>
    <c:if test="${success != null}">
        <div class="alert alert-success alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Success!</strong><c:out value="${success}"/>
        </div>
    </c:if>
</div>
</body>
</html>
