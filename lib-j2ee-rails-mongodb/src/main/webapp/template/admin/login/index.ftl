<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.js"></script>
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