'use strict'

angular.module('AvailFormsEx').controller('ManterUsuarioCtrl', function($scope, $rootScope, $q, ngNotify, $timeout, $http, UiSrv, RestSrv, $routeParams, SERVICE_PATH, ModalSrv) {

    //Variaveis obrigatórias para uso interno o framework
    $scope.urlObj = SERVICE_PATH.urlPrivate + '/usuario';
    $scope.telaList = 'usuario';
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