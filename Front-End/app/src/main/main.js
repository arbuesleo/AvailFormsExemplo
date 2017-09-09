'user strict';

angular.module('AvailFormsEx').
controller('mainCtrl', function($scope, $rootScope, $location, LoginLogoutSrv, RestSrv, SERVICE_PATH, $localStorage) {

    var urlPrivateUsuario = SERVICE_PATH.urlPrivate + '/usuario';

    $scope.hasAnyPermission = function(authorities) {
        var hasPermission = false;

        $rootScope.authDetails.permissions.forEach(function(permission) {
            authorities.forEach(function(authority) {
                if (permission.authority === authority) {
                    hasPermission = true;
                }
            });
        });

        return hasPermission;
    };

    $scope.logout = function() {
        LoginLogoutSrv.logout();
    };

    $scope.trocarDeFarmacia = function() {
        LoginLogoutSrv.setFarmaciaLogada();
    };

    $scope.setFarmaciaLogada = function(idFarmacia) {
        RestSrv.find(urlPrivateUsuario + "/setFarmaciaLogada?id=" + idFarmacia, function(response) {
            $rootScope.authDetails.idEmpresa = response.data.data.activeUser.idEmpresaLogada;
            $rootScope.authDetails.nomeEmpresa = response.data.data.activeUser.nomeEmpresaLogada;
            $localStorage.authDetails = $rootScope.authDetails;
            $('#pesquisarFarmaciaLogin').modal('hide');
            $location.path("/");
        });
    }


});