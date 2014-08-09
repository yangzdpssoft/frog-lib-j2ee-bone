
seajs.config({
    base: "./sea-modules/"
});
seajs.use('main');

var app = angular.module('app', ['ngRoute', 'ngAnimate', 'route-segment', 'view-segment']);

app.config(function($routeSegmentProvider, $routeProvider) {

    // Configuring provider options

    $routeSegmentProvider.options.autoLoadTemplates = true;

    $routeProvider.otherwise({redirectTo: '/section1'});
}) ;

function MainCtrl($scope, $routeSegment, loader) {


}

