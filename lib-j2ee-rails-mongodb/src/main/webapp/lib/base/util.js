function isSuccess(response){
    return response.result === 'success';
}
function isError(response){
    return response.result === 'error';
}
function isLogin(response){
    return response.result === 'login';
}
function isFailure(response){
    return response.result === 'failure';
}
function isInput(response){
    return response.result === 'input';
}
function dealExceptionResult(response){
    if(isError(response) || isFailure(response)){
        $.messager.show({title: '错误', msg:  '失败原因：' + response.data});
    }else if(isInput(response)){
        $.messager.show({title: '提示', msg:  response.data});
    }else if(isLogin(response)){
        $.messager.show({title: '错误', msg:  '失败原因：请先登录！'});
    }
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
