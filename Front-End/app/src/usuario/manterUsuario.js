'use strict'

angular.module('AvailFormsEx').controller('ManterUsuarioCtrl', function($scope, $sce, $rootScope, $q, ngNotify, $timeout, $http, UiSrv, RestSrv, $routeParams, SERVICE_PATH, ModalSrv) {

    //Variaveis obrigatórias para uso interno o framework
    $scope.urlObj = SERVICE_PATH.urlPrivate + '/usuario';
    $scope.telaList = 'usuario';
    //Fim das variaveis obrigatorias

    //Campos Adicionais
    $scope.camposAdicionais = ["<label for='email' class='col-sm-2 control-label'>Usuário*</label> <div class='col-sm-4'> <div class='input-group'> <span class='input-group-addon'><i class='fa fa-align-justify'></i></span> <input type='text' class='form-control' ng-model='objEdit.email' disabled> </div> </div>"];

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