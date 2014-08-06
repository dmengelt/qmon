'use strict';

var hornetqMonitorApp = angular.module('hornetqMonitorApp', [
    'ngCookies',
    'ngResource',
    'ngRoute'
]);

hornetqMonitorApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainController'
            }).
            otherwise({
                redirectTo: '/'
            });
    }
]);

