app.controller('form', function($scope, _ajax){
    initForm();
    $('.view').fadeIn();
    _ajax.postCall('http://www.baidu.com');
    $scope.lists = ['1', '2', '3', '4'];
});