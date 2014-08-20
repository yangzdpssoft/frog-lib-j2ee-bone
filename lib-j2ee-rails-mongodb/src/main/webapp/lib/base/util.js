function isSuccess(response){
    return response.result == 'success';
}
function isError(response){
    return response.result == 'error';
}
function isLogin(response){
    return response.result == 'login';
}
function isFailure(response){
    return response.result == 'failure';
}
function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)    return;
    }
}