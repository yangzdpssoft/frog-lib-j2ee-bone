var app = angular.module('app', ['ngRoute', 'ngAnimate']);
app.config(['$routeProvider', '$controllerProvider', function ($routeProvider, $controllerProvider, applyProvider) {
    app.register = function(name, constructor) {
        return $controllerProvider.register(name, constructor)
    };
    $routeProvider
        .when('/form', {
            templateUrl: 'views/form.html',
            controller : 'form',
            resolve: {
                resolver: function ($rootScope, $q) {
//                    var deferred = $q.defer();
//                    seajs.use('form', function(form){
//                        $rootScope.$apply(function () {
//                            deferred.resolve();
//                        });
//                    });
//                    var t = deferred.promise;
//                    return t;
                }
            }
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