'use strict';

angular.module('hornetqMonitorApp')
    .controller('MainController', function ($scope, $http, $route) {

        $scope.defaultEnv = '';

        $scope.getEnvironmenetInfo = function () {
            $http.get('/monitor/system/environments').success(function(data, status, headers, config) {
                $scope.environments = data.environments;
                $scope.defaultEnv = $scope.environments[0];
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to lookup the environment information');
            });
        };

        $scope.updateEnvironment = function () {
            console.log('changing environment to ' + $scope.defaultEnv.name);

            $scope.env = {
                environmentName: $scope.defaultEnv.name
            };

            $http.put('/monitor/system/environments', $scope.env).success(function(data, status, headers, config) {
                console.log('Successfully updated the environment to ' + $scope.defaultEnv.name);
                $route.reload();
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to update the environment');
            });

        };

    });
