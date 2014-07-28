<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <#include "/template/admin/include/bootstrap.ftl">
</head>
<style type="text/css">
    /* box ------------------------------------------------------ */

    #box {
        text-align: center;
        min-width: 600px;
        font-size: 3em;
        font-weight: bold;
        -webkit-backface-visibility: hidden; /* fixes flashing */
    }


    /* flashlight ------------------------------------------------------ */

    #flashlight {
        color: hsla(0,0%,0%,0);
        perspective: 80px;
        outline: none;
    }


    /* flash ------------------------------------------------------ */

    #flash {
        display: inline-block;
        text-shadow: #eee 0 0 1px, #fff 0 -1px 2px, #fff 0 -3px 20px;
        transition: margin-left 0.3s cubic-bezier(0, 1, 0, 1);
    }

    #box:hover #flash {
        margin-left: 20px;
        transition: margin-left 1s cubic-bezier(0, 0.75, 0, 1);
    }


    /* light ------------------------------------------------------ */

    #light {
        display: inline-block;
        text-shadow: #d9534f 0 0 1px, rgba(255,255,255,0.1) 0 1px 3px;
    }

    #box:hover #light {
        text-shadow: #d9534f 0 0 4px, #fcffbb 0 0 20px;
        transform: rotateY(-60deg);
        transition:         transform 0.3s cubic-bezier(0, 0.75, 0, 1), text-shadow 0.1s ease-out;
    }

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
    <div class="row" style="background: #5bc0de;height: 350px;margin-bottom: 20px;">
        <div class="col-md-4"></div>
        <div class="col-md-4" style="padding-top: 200px;">
            <div id="box">
                <p id="flashlight">
                    <span id="flash">协和医院</span>
                    <span id="light">烧伤科</span>
                </p>
            </div>
            <div id="sys" style="float: right;"><h1><a href="javascript:">采集系统</a></h1></div>
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