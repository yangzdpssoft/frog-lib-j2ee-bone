var app = angular.module('app', ['ngRoute', 'ngAnimate']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/form', {
            templateUrl: 'views/form.html',
            controller : 'form'
        })
        .when('/list', {
            templateUrl: 'views/list.html',
            controller : function(){
                $('.view').fadeIn();
            },
            resolve: {
                resolver: function () {

                }
            }
        })
        .when('/detail', {
            templateUrl: 'views/detail.html',
            controller : function(){
                $('.view').fadeIn();
            },
            resolve: {
                resolver: function () {

                }
            }
        })
        .otherwise({
            redirectTo: '/list'
        });
}]);


