<!DOCTYPE html>
<html>
<head>
    <script src="angular.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var app = angular.module('myapp',[]);
        app.controller('mytest',function($scope){
            $scope.test="hello word";
        });
    </script>
    <title>learing argularjs--widuu</title>
</head>
<body ng-app='myapp' ng-controller='mytest' >
<input ng-model='test'>{{test}}
</body>
</html>