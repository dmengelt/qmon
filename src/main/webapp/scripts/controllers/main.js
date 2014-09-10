'use strict';

angular.module('hornetqMonitorApp')
    .controller('MainController', function ($scope, $http) {

        $scope.getEnvironmenetInfo = function () {
            $http.get('/monitor/info/environment').success(function(data, status, headers, config) {
                $scope.environment = data;
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to lookup the environment information');
            });
        };

    });
