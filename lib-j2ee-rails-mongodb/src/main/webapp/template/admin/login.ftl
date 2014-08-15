<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8" />
    <#include "/lib/include/bootstrap.ftl">
</head>
<style type="text/css">


    #sys h1 a {
        display: block; text-decoration: none;
        font: 38px Helvetica, Arial, Sans-Serif; letter-spacing: -5px;
        text-align: center;
        color: #fff; text-shadow: 0px 3px 8px #2a2a2a;
    }
    #sys h1 a:hover {
        color: #1A34FF; text-shadow: 0px 5px 8px #2a2a2a;
    }

</style>
<body>
<div class="container-fluid">
    <div class="row" style="background: #3399FF;height: 350px;margin-bottom: 20px;">
        <div class="col-md-4"></div>
        <div class="col-md-4" style="padding-top: 200px;">
            <div style=" font-family: 微软雅黑; font-size: 42px; padding:20px; font-weight:bold;">
                <span style="text-shadow: 2px 2px 2px #000, 0px 0px 10px #fff, 0px 0px 20px #fff; color: #0099FF;">协和医院烧伤科</span><br>
                <span style="text-shadow: 2px 2px 2px #000, 0px 0px 10px #fff, 0px 0px 20px #fff; color: #F9F9F9; float: right;font-size: 28px;">会诊管理平台</span><br>
            </div>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="row" style="height: 300px;">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form class="form-signin" role="form">
                <input type="email" class="form-control" placeholder="账号" required autofocus>
                <br/>
                <input type="password" class="form-control" placeholder="密码" required>
                <br/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="row" >
        <div class="col-md-4"></div>
        <div class="col-md-4">

        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>