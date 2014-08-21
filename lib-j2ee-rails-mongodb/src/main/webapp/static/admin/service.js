app.service('_ajax', function($http){
    this.postCall = function(url, data){
        var result = null;
        $http({method : 'post', data : data, url : url}).success(function(response, status){
            if(isSuccess(response)){
                result = response.data;
            }else if(isError(response) || isFailure(response)){

            }else if(isLogin(response)){

            }
        }).error(function(data, status) {

        });
    }
});