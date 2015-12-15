<%--
  Created by IntelliJ IDEA.
  User: Jayavardhan
  Date: 12/9/15
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        .topcorner{
            position:absolute;
            top:10px;
            right:10px;
        }

    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>
<body ng-app="myApp" class="ng-cloak">
<div class="generic-container" ng-controller="UserController as ctrl" >
    <div class="panel panel-default" ng-init="ctrl.get(${user.id})">
        <div class="panel-heading" >
            <div class="container">
                <div>
                    <h3 align="center">
                        Task List
                    </h3>
                </div>
                <div class="topcorner">
                    <a href="/logout">Logout</a>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <div class="container">
                <div>
                    <h3 align="center">
                        {{user.name}}
                    </h3>
                    <p></p>
                </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <ng-view></ng-view>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.28/angular-route.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="<c:url value='/static/library/ui-bootstrap-tpls-0.12.0.min.js' />"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/user_service.js' />"></script>
<script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
</body>
</html>
