'use strict';

angular.module('hornetqMonitorApp')
    .controller('QueueController', function ($scope, $http) {

        $scope.loadQueues = function () {
            $http.get('/monitor/queues').success(function(data, status, headers, config) {
                $scope.queues = data.queues;
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to lookup the configured queues');
            });

        };

    });
