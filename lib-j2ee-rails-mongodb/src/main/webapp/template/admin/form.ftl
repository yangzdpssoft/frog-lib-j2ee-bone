<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${ctx}/lib/fileInput/css/fileinput.min.css">
    <script src="${ctx}/lib/fileInput/js/fileinput.min.js"></script>

    <script src="${defaultjs}" type="text/javascript"></script>
</head>

<body style="padding: 40px;">
<div class="container-fluid">
    <div class="row">
        <button type="button" class="btn btn-success" style="margin-left: 10px;"><i class="glyphicon glyphicon-search" style="color: #ffffff;"></i>&nbsp;保存&nbsp;</button>
        <button type="button" class="btn btn-default" style="margin-left: 10px;"><i class="glyphicon glyphicon-flash"></i>&nbsp;退出&nbsp;</button>
    </div>
    <br/>
    <div class="row">
        <form class="form-horizontal" >
            <div class="form-group">
                <label for="firstname" class="col-md-2 control-label">名字</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="firstname"
                           placeholder="请输入名字">
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-md-2 control-label">名字1111</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="firstname"
                           placeholder="请输入名字">
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-md-2 control-label">名字123123</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="firstname"  placeholder="请输入名字">
                </div>
            </div>
        </form>
    </div>

    <div class="row">
        <div class="form-group">
            <input id="file-1" type="file" multiple=true data-preview-file-type="any">
        </div>
    </div>
</div>
</body>
</html>