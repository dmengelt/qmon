'use strict';

angular.module('hornetqMonitorApp')
    .controller('TopicDetailController', function ($rootScope, $scope, $http, $routeParams) {
        $scope.topicName = $routeParams.topicName;

        $scope.topicDetail = [];

        $scope.loadTopicDetails = function () {
            $http.get('/monitor/topics/' + $scope.topicName).success(function(data, status, headers, config) {
                $scope.topicDetail = data;
            }).error(function(data, status, headers, config) {
                console.log('An error occured while trying to get the topic details for topic: ' + $scope.topicName);
            });

        };
    });
