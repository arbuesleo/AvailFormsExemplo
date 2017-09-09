'use strict';

angular.module('AvailFormsEx')
    .controller('EstadoCtrl', function($rootScope, $scope, $location, RestSrv, UiSrv, $timeout, SERVICE_PATH) {
        //Variaveis obrigatórias para uso interno o framework
        $scope.urlObj = SERVICE_PATH.urlPrivate + '/estado';
        $scope.telaEdit = 'manterEstado';
        //Fim das variaveis obrigatorias

        $scope.pesquisa = function() {
            RestSrv.find($scope.urlObj, function(response) {
                $scope.objListagem = response.data;
            });
        };

        $scope.pesquisa();

    });