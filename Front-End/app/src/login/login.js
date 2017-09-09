'use strict';

angular.module('AvailFormsEx')
    .controller('LoginCtrl', function($rootScope, $scope, LoginLogoutSrv, $location, $timeout) {

        $scope.login = function(email, password) {
            LoginLogoutSrv.login(email, password);
        };

        if ($rootScope.authDetails.authenticated) {
            $location.path('/home');

        }

    });