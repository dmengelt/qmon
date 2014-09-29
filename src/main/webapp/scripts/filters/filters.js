'use strict';

angular.module('hornetqMonitorApp')
    .filter('unknown', function() {
        return function(input) {

            if(input == undefined || input.length == 0) {
                return "unknown";
            }

            return input;
        };
    });
