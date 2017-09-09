'use strict';

var url = 'http://localhost:9999/api';

angular.module('AvailFormsEx', [
        'AvFormsGenerator'
    ])
    .config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'src/login/login.html',
                controller: 'LoginCtrl'

            })
            .when('/login', {
                templateUrl: 'src/login/login.html',
                controller: 'LoginCtrl'
            })
            .when('/home', {
                templateUrl: 'src/home/home.html',
                controller: 'HomeCtrl'
            })
            .when('/manterUsuario/:id', {
                templateUrl: 'src/usuario/manterUsuario.html',
                controller: 'ManterUsuarioCtrl'
            })
            .when('/usuario', {
                templateUrl: 'src/usuario/usuario.html',
                controller: 'UsuarioCtrl'
            })
            .when('/cidade', {
                templateUrl: 'src/cidade/cidade.html',
                controller: 'CidadeCtrl'
            })
            .when('/manterCidade/:id', {
                templateUrl: 'src/cidade/manterCidade.html',
                controller: 'ManterCidadeCtrl'
            })
            .when('/estado', {
                templateUrl: 'src/estado/estado.html',
                controller: 'EstadoCtrl'
            })
            .when('/manterEstado/:id', {
                templateUrl: 'src/estado/manterEstado.html',
                controller: 'ManterEstadoCtrl'
            })
            .when('/naoAutorizado', {
                templateUrl: 'src/erros/naoAutorizado.html'
            })
            .otherwise({
                redirectTo: '/'
            });
    })
    .config(function($httpProvider) {

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.interceptors.push('httpRequestInterceptor');

    }).constant('SERVICE_PATH', {

        'buscaCepWs': 'https://viacep.com.br/ws/',
        'mapsWs': 'https://www.google.com/maps/embed/v1/place?q=',
        'urlPrivate': url + '/private',
        'urlPublic': url + '/public',

    }).run(function($rootScope, ngNotify, LoginLogoutSrv) {

        $rootScope.authDetails = { name: '', authenticated: false, permissions: [] };

        ngNotify.config({
            position: 'bottom',
            duration: 2000,
            theme: 'pitchy',
            button: true
        });

        LoginLogoutSrv.verifyAuth();
    });