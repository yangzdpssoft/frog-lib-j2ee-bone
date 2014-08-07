<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8" />
    <#include "/lib/include/bootstrap.ftl">
</head>
<body>
<header id="header">
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-default " role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        <img alt="Brand" src="${ctx}/template/admin/static/images/brand.png" width="30" height="30" style="margin-top: -5px;">
                        <span style="color: darkcyan;font-weight: bold;font-size: 24px;">协和医院烧伤科</span>
                        <small>会诊管理平台</small>
                    </a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#"><i class="glyphicon glyphicon-globe" style="padding-right: 10px;color: darkgoldenrod;"></i>会诊平台</a></li>
                        <li><a href="#"><i class="glyphicon glyphicon-film" style="padding-right: 10px;color: darkgreen;"></i>采集系统</a></li>
                        <li><a href="#"><i class="glyphicon glyphicon-fire" style="padding-right: 10px;color: #CC2222;"></i>公众服务</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><i class="glyphicon glyphicon-pencil" style="padding-right: 10px;"></i>个人资料</a></li>
                        <li><a href="#"><i class="glyphicon glyphicon-lock" style="padding-right: 10px;"></i>修改密码</a></li>
                        <p class="navbar-text"><i class="glyphicon glyphicon-user" style="padding-right: 10px;color: #0000FF;"></i>林志忠</p>
                        <li><a href="#"><i class="glyphicon glyphicon-off" style="padding-right: 10px;"></i>退出</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>
</div>
</header>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="#" class="list-group-item active">
                    Cras justo odio
                </a>
                <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
                <a href="#" class="list-group-item">Morbi leo risus</a>
                <a href="#" class="list-group-item">Porta ac consectetur ac</a>
                <a href="#" class="list-group-item">Vestibulum at eros</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="row">
                <div class="col-md-4">
                    <div class="input-group">
                        <span class="input-group-addon">用么发</span>
                        <input type="text" class="form-control" />
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="input-group">
                        <span class="input-group-addon">用么发</span>
                        <input type="text" class="form-control" />
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="input-group">
                        <span class="input-group-addon">用么发</span>
                        <input type="text" class="form-control" />
                    </div>
                </div>
            </div>
            <div  style="margin:20px 0px; float: right;">
                <button type="button" class="btn btn-primary" style="margin-left: 10px;"><i class="glyphicon glyphicon-search" style="color: #ffffff;"></i>&nbsp;查询&nbsp;</button>
                <button type="button" class="btn btn-success" style="margin-left: 10px;"><i class="glyphicon glyphicon-plus" style="color: #ffffff;"></i>&nbsp;新增&nbsp;</button>
            </div>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td >1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <td>Otto</td>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@TwBootstrap</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>