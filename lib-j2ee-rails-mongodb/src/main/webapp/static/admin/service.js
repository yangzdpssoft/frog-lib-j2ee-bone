app.service('_ajax', function($http){
    this.postCall = function(url){
        $http({method : 'post', url : url}).success(function(data, status){

        }).error(function(data, status) {

        });
    }
});