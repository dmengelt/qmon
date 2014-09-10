'use strict';

angular.module('hornetqMonitorApp')
    .controller('TopicController', function ($rootScope, $scope, $http) {

        $rootScope.topics = [];

        $scope.loadTopics = function () {
            $http.get('/monitor/topics').success(function(data, status, headers, config) {
                $rootScope.topics = data.topics;
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to lookup the configured topics');
            });

        };

    });
