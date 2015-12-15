<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jayavardhan
  Date: 12/5/15
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Home Page</title>
  <style>
    .username.ng-valid {
      background-color: lightgreen;
    }
    .username.ng-dirty.ng-invalid-required {
      background-color: red;
    }
    .username.ng-dirty.ng-invalid-minlength {
      background-color: yellow;
    }

    .email.ng-valid {
      background-color: lightgreen;
    }
    .email.ng-dirty.ng-invalid-required {
      background-color: red;
    }
    .email.ng-dirty.ng-invalid-email {
      background-color: yellow;
    }
    </style>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>
<body>
<div class="generic-container" >
    <div class="panel panel-default" >
        <div class="panel-heading" >
            <div class="container">
                <div>
                    <h3 align="center">
                        Task List
                    </h3>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <div class="container">
                <div>
                    <h3 align="center">
                        <button onclick="login()">Login</button>
                    </h3>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    login = function() {
        var url = window.location.href;
        var buldingUrl = url + '/login';
        window.location = buldingUrl;
    }
</script>
</body>
</html>
