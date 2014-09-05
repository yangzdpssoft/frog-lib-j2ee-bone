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

function fetchProp(obj, prop, deepKey){
    var result = {};
    $.each(prop, function(){
        if(this.toString() === deepKey){
            var deep = obj[deepKey];
            if(deep){
                if($.isArray(deep)){
                    var sub = [];
                    for(var i = 0; i < deep.length; i++){
                        sub[i] = fetchProp(deep[i], prop, deepKey);
                    }
                    result[deepKey] = sub;
                }else{
                    result[deepKey] = fetchProp(obj[deepKey], prop, deepKey);
                }
            }
        }else{
            result[this.toString()] = obj[this.toString()];
        }
    });
    return result;
}
