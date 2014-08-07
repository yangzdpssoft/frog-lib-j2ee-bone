<!doctype html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.5/angular.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var app = angular.module('myapp',[])
                .value('testvalue','word');
        app.controller('mytest',function($scope,$http,testvalue){
            $http.get("https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.5/angular.min.js")
                    .success(function(data){
                        $scope.test= data;
                    })
                    .error(function(data,status,headers,config){
                        //一些错误处理的代码
                    });
        });
    </script>
    <title>learing argularjs--widuu</title>
</head>
<body ng-app='myapp' ng-controller='mytest' >
<input ng-model='test'>{{test}}
</body>
</html>