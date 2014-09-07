'use strict';

angular.module('hornetqMonitorApp')
    .controller('MainController', function ($scope, $http) {

        $scope.loadQueues = function () {
            $http.get('/monitor/queues').success(function(data, status, headers, config) {
                $scope.queues = data.queues;
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to lookup the configures queues');
            });
        };

        $scope.loadTopics = function () {
            $http.get('/monitor/topics').success(function(data, status, headers, config) {
                $scope.topics = data.topics;
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to lookup the configured topics');
            });
        };

        $scope.getEnvironmenetInfo = function () {
            $http.get('/monitor/info/environment').success(function(data, status, headers, config) {
                $scope.environment = data;
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to lookup the environment information');
            });
        };

    });
