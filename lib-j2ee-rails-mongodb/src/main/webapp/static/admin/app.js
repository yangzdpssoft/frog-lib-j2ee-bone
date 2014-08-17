//seajs
seajs.config({
    base: "./sea-modules/"
});
seajs.use('main');

//angularjs
var app = angular.module('app', ['ngRoute', 'ngAnimate', 'route-segment', 'view-segment']);
app.config(function($routeSegmentProvider, $routeProvider) {
    $routeSegmentProvider.options.autoLoadTemplates = true;
    $routeProvider.otherwise({redirectTo: '/section1'});
}) ;

//左侧导航菜单
(function($){
    $(function(){
        $('#sidebar').metisMenu({});
        $('.selectpicker').selectpicker();
        $("input:checkbox").bootstrapSwitch();
        $('#bs-example-navbar-collapse-1 ul li').click(function(){
            if(!$(this).attr('class')){
                $('#bs-example-navbar-collapse-1 ul li').removeClass('active');
                $(this).addClass('active');
            }
        });
    });
})(jQuery);


