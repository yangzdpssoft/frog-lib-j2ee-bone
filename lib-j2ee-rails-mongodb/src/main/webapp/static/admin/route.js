var app = angular.module('app', ['ngRoute', 'ngAnimate']);
app.config(['$routeProvider', '$controllerProvider', function ($routeProvider, applyProvider) {
    app.register = function(name, constructor) {
        return $controllerProvider.register(name, constructor)
    };
    $routeProvider
        .when('/form', {
            templateUrl: 'views/form.html',
            controller : 'form'
        })
        .when('/list', {
            templateUrl: 'views/list.html',
            controller : function(){
                $('.view').fadeIn();
            }
        })
        .when('/detail', {
            templateUrl: 'views/detail.html',
            controller : function(){
                $('.view').fadeIn();
            }
        })
        .otherwise({
            redirectTo: '/list'
        });
}]);