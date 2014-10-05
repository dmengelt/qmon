'use strict';

var hornetqMonitorApp = angular.module('hornetqMonitorApp', [
    'ngCookies',
    'ngResource',
    'ngRoute',
    'nya.bootstrap.select'
]);

hornetqMonitorApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainController'
            }).
            when('/queues', {
                templateUrl: 'views/queues.html',
                controller: 'QueueController'
            }).
            when('/topics', {
                templateUrl: 'views/topics.html',
                controller: 'TopicController'
            }).
            when('/topics/:topicName', {
                templateUrl: 'views/topic-detail.html',
                controller: 'TopicDetailController'
            }).
            otherwise({
                redirectTo: '/'
            });
    }
]);

