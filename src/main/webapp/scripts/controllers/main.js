'use strict';

angular.module('hornetqMonitorApp')
    .controller('MainController', function ($scope, $http) {

        $scope.getEnvironmenetInfo = function () {
            $http.get('/monitor/system/environments').success(function(data, status, headers, config) {
                $scope.environments = data.environments;
                $scope.defaultEnv = $scope.environments[0];
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to lookup the environment information');
            });
        };

    });
