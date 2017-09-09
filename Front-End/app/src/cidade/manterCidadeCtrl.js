'use strict';

angular.module('AvailFormsEx')
    .controller('ManterCidadeCtrl', function($rootScope, $scope, $location, RestSrv, UiSrv, $timeout, SERVICE_PATH, $routeParams) {
        //Variaveis obrigatórias para uso interno o framework
        $scope.urlObj = SERVICE_PATH.urlPrivate + '/cidade';
        $scope.telaList = 'cidade';
        //Fim das variaveis obrigatorias

        var carregaPagina = function() {
            if ($routeParams.id != 0) {
                RestSrv.find($scope.urlObj + '/pesquisaPorId?id=' + $routeParams.id, function(response) {
                    $scope.objEdit = response.data;
                }, true);
            } else {
                $scope.objEdit = {
                    "id": 0
                };
            }
        };

        carregaPagina();

        $scope.salvaRegistro = function() {
            $scope.save($scope.objEdit);
        };

    });