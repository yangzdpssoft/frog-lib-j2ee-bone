/**
 * Created by yangzdpssoft on 2014/8/7.
 */
//seajs.config({
//    base: "./sea-modules/"
//});
//seajs.use('main');

var app = angular.module('app', ['ngRoute', 'ngAnimate', 'route-segment', 'view-segment']);

app.config(function($routeSegmentProvider, $routeProvider) {

    // Configuring provider options

    $routeSegmentProvider.options.autoLoadTemplates = true;

    // Setting routes. This consists of two parts:
    // 1. `when` is similar to vanilla $route `when` but takes segment name instead of params hash
    // 2. traversing through segment tree to set it up

    $routeSegmentProvider

        .when('/section1',          's1')
        .when('/section1/prefs',    's1.prefs')
        .when('/section1/:id',      's1.itemInfo')
        .when('/section1/:id/X',    's1.itemInfo.tab1')
        .when('/section1/:id/Y',    's1.itemInfo.tab2')

        .when('/section2',          's2')
        .when('/section2/:id',      's2.itemInfo')

        .when('/section3',          's3')

        .segment('s1', {
            templateUrl: 'templates/section1.shtml',
            controller: MainCtrl})

        .within('s1')

        .segment('home', {
            default: true,
            templateUrl: 'templates/section1/home.shtml'})

        .segment('itemInfo', {
            templateUrl: 'templates/section1/item.shtml',
            controller: Section1ItemCtrl,
            dependencies: ['id']})

        .within()

        .segment('tab1', {
            default: true,
            templateUrl: 'templates/section1/tabs/tab1.shtml'})

        .segment('tab2', {
            templateUrl: 'templates/section1/tabs/tab2.shtml'})

        .up()

        .segment('prefs', {
            templateUrl: 'templates/section1/prefs.shtml'})

        .up()

        .segment('s2', {
            templateUrl: 'templates/section2.shtml',
            controller: MainCtrl})

        .within()

        .segment('itemInfo', {
            templateUrl: 'templates/section2/item.shtml',
            dependencies: ['id']})

        .up()

        .segment('s3', {
            templateUrl: 'templates/section3.shtml'})


    // Also, we can add new item in a deep separately. This is useful when working with
    // routes in every module individually

    $routeSegmentProvider

        .when('/section1/:id/Z',    's1.itemInfo.tab3')

        .within('s1')
        .within('itemInfo')
        .segment('tab3', {
            templateUrl: 'templates/section1/tabs/tab3.shtml'})


    // This is some usage of `resolve`, `untilResolved` and `resolveFailed` features

    $routeSegmentProvider

        .when('/invalid-template', 's1.invalidTemplate')
        .when('/invalid-data', 's1.invalidData')
        .when('/slow-data', 's1.slowDataSimple')
        .when('/slow-data-loading', 's1.slowDataLoading')
        .when('/inline-view', 's1.inlineParent')
        .when('/section1/:id/slow',    's1.itemInfo.tabSlow')

        .within('s1')
        .segment('invalidTemplate', {
            templateUrl: 'this-does-not-exist.shtml',    // 404
            resolveFailed: {
                templateUrl: 'templates/error.shtml',
                controller: 'ErrorCtrl'
            }
        })
        .segment('invalidData', {
            templateUrl: 'templates/section1/home.shtml',     // Correct!
            resolve: {
                data: function($q) {
                    return $q.reject('ERROR DESCRIPTION');     // Failed to load data
                }
            },
            resolveFailed: {
                templateUrl: 'templates/error.shtml',
                controller: 'ErrorCtrl'
            }
        })
        .segment('slowDataSimple', {
            templateUrl: 'templates/section1/slow-data.shtml',
            controller: 'SlowDataCtrl',
            resolve: {
                data: function($timeout, loader) {
                    loader.show = true;
                    return $timeout(function() { return 'SLOW DATA CONTENT'; }, 2000);
                }
            }
        })
        .segment('slowDataLoading', {
            templateUrl: 'templates/section1/slow-data.shtml',
            controller: 'SlowDataCtrl',
            resolve: {
                data: function($timeout) {
                    return $timeout(function() { return 'SLOW DATA CONTENT'; }, 2000);
                }
            },
            untilResolved: {
                templateUrl: 'templates/loading.shtml'
            }
        })
        .segment('inlineParent', {
            templateUrl: 'templates/section1/inline-view.shtml'
        })
        .within()
        .segment('inlineChildren', {
            // no template here
            controller: 'SlowDataCtrl',
            default: true,
            resolve: {
                data: function($timeout) {
                    return $timeout(function() { return 'SLOW DATA CONTENT'; }, 2000);
                }
            },
            untilResolved: {
                templateUrl: 'templates/loading.shtml'
            }
        })
        .up()

        .within('itemInfo')
        .segment('tabSlow', {
            templateUrl: 'templates/section1/slow-data.shtml',
            controller: 'SlowDataCtrl',
            resolve: {
                data: function($timeout) {
                    return $timeout(function() { return 'SLOW DATA CONTENT'; }, 2000);
                }
            },
            untilResolved: {
                templateUrl: 'templates/loading.shtml'
            }
        })
    $routeProvider.otherwise({redirectTo: '/section1'});
}) ;

app.value('loader', {show: false});

function MainCtrl($scope, $routeSegment, loader) {

    $scope.$routeSegment = $routeSegment;
    $scope.loader = loader;

    $scope.$on('routeSegmentChange', function() {
        loader.show = false;
    })
}

function Section1Ctrl($scope, $routeSegment) {

    $scope.$routeSegment = $routeSegment;
    $scope.test = { btnClicked: false };
    $scope.items = [ 1,2,3,4,5 ];
}

function Section1ItemCtrl($scope, $routeSegment) {

    $scope.$routeSegment = $routeSegment;
    $scope.item = { id: $routeSegment.$routeParams.id };
    $scope.test = { textValue: '' };
}

function Section2Ctrl($scope, $routeSegment) {

    $scope.$routeSegment = $routeSegment;
    $scope.test = { textValue: '' };
    $scope.items = [ 1,2,3,4,5,6,7,8,9 ];
}

function ErrorCtrl($scope, error) {
    $scope.error = error;
}

function SlowDataCtrl($scope, data, loader) {
    loader.show = false;
    $scope.data = data;
}
