'use strict';

angular.module('AvailFormsEx')
    .service('LoginLogoutSrv', function($http, $cookies, $rootScope, $location, $localStorage, ngNotify, SERVICE_PATH, RestSrv) {
        var serviceFactory = {};

        var urlLogin = SERVICE_PATH.urlPublic + '/login';
        var urlLogout = SERVICE_PATH.urlPublic + '/logout';
        var urlPrivateUsuario = SERVICE_PATH.urlPrivate + '/usuario';



        serviceFactory.login = function(email, password) {
            var requestParams = {
                method: 'GET',
                url: urlLogin,
                headers: {
                    'Content-Type': 'application/json',
                    'authorization': 'Basic ' + btoa(email + ':' + password)
                }
            };

            $http(requestParams).then(
                function success(response) {
                    var data = response.data;

                    if (data.name) {
                        $rootScope.authDetails = { name: data.name, authenticated: data.authenticated, permissions: data.authorities, email: data.principal.email, imagem: data.principal.imagemPerfil, idEmpresa: undefined, nomeEmpresa: undefined };
                        $localStorage.authDetails = $rootScope.authDetails;
                        $location.path('/home');
                        ngNotify.set('Bem Vindo ' + data.name + '.', 'success');
                    } else {
                        $rootScope.authDetails = { name: '', authenticated: false, permissions: [], imagem: {}, email: '', idEmpresa: undefined, nomeEmpresa: undefined };
                        ngNotify.set('E-mail ou senha inválidos.', { type: 'error', duration: 5000 });
                    }
                },
                function failure(response) {
                    $rootScope.authDetails = { name: '', authenticated: false, permissions: [], imagem: {}, email: '', idEmpresa: undefined, nomeEmpresa: undefined };
                    ngNotify.set('E-mail ou senha inválidos.', { type: 'error', duration: 5000 });
                }
            );
        };

        serviceFactory.logout = function() {
            var requestParams = {
                method: 'POST',
                url: urlLogout
            };

            $http(requestParams).finally(function success(response) {
                delete $localStorage.authDetails;
                $rootScope.authDetails = { name: '', authenticated: false, permissions: [], imagem: {}, email: '', idEmpresa: undefined, nomeEmpresa: undefined };
                $location.path("/");
            });
        };

        serviceFactory.verifyAuth = function() {
            if ($localStorage.authDetails) {
                $rootScope.authDetails = $localStorage.authDetails;
            }
        };


        return serviceFactory;
    });