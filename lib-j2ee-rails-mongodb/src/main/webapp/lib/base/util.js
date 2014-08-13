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