'use strict';

angular.module('hornetqMonitorApp')
    .controller('QueueController', function ($rootScope, $scope, $http, $route) {

        $rootScope.queues = [];

        $scope.loadQueues = function () {
            $http.get('/monitor/queues').success(function(data, status, headers, config) {
                $rootScope.queues = data.queues;
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to lookup the configured queues');
            });

        };

        $scope.clearMessages = function (queueName) {
            $http.put('/monitor/queues/' + queueName + '/messages/clear').success(function(data, status, headers, config) {
                console.log('Successfully cleared messages for queue ' + queueName);
                $route.reload();
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to clear the message for queue ' + queueName);
            });

        };

    });
