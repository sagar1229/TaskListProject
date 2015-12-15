/**
 * Created by Jayavardhan on 12/9/15.
 */


'use strict';

var App = angular.module('myApp',[ 'ngRoute','ui.bootstrap']);

App.config(['$routeProvider','$locationProvider',
    function($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: '/static/pages/home.html',
                controller: 'UserController'
            }).
            otherwise({
                redirectTo: '/',
            });
    }]);
