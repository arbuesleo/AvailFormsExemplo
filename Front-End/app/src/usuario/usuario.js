'use strict'

angular.module('AvailFormsEx').controller('UsuarioCtrl', function($scope, $timeout, $http, UiSrv, RestSrv, SERVICE_PATH, ModalSrv, $uibModal, $log, $document) {
    //Variaveis obrigatórias para uso interno o framework
    $scope.urlObj = SERVICE_PATH.urlPrivate + '/usuario';
    $scope.telaEdit = 'manterUsuario';
    //Fim das variaveis obrigatorias

    $scope.pesquisa = function() {
        RestSrv.find($scope.urlObj, function(response) {
            $scope.objListagem = response.data;
        });
    };

    $scope.pesquisa();
});