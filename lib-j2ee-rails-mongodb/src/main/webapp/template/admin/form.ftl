<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<#include "/lib/include/common.ftl">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${ctx}/lib/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/lib/base/bootstrap3.chinese.css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <!-- 文件上传 -->
    <link rel="stylesheet" href="${ctx}/lib/webUploader/0.1.4/webuploader.css">
    <script src="${ctx}/lib/webUploader/0.1.4/webuploader.js"></script>
    <script src="${ctx}/lib/base/upload.js" type="text/javascript"></script>
    <!-- metisMenu -->
    <link rel="stylesheet" href="${ctx}/lib/font-awesome/4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/lib/metisMenu/1.1.0/metisMenu.min.css">
    <script src="${ctx}/lib/metisMenu/1.1.0/metisMenu.min.js"></script>
    <link rel="stylesheet" href="${defaultcss}">
    <script src="${defaultjs}"></script>
</head>

<body>
<div class="container-fluid">
    <header id="header">
        <div class="row">
            <nav class="navbar navbar-inverse " role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Brand</a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="#">Link</a></li>
                            <li><a href="#">Link</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">One more separated link</a></li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">Link</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </nav>
        </div>
    </header>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-3 col-md-2">
            <aside class="sidebar">
                <nav class="sidebar-nav">
                    <ul id="menu">
                        <li class="active">
                            <a href="#">
                                <span class="sidebar-nav-item-icon fa fa-github fa-lg"></span>
                                <span class="sidebar-nav-item">metisMenu</span>
                                <span class="fa arrow"></span>
                            </a>
                            <ul>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-star"></span>
                                        Star
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu/issues">
                                        <span class="sidebar-nav-item-icon fa fa-exclamation-triangle"></span>
                                        Issues
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">
                                <span class="sidebar-nav-item-icon fa fa-github fa-lg"></span>
                                <span class="sidebar-nav-item">metisMenu</span>
                                <span class="fa arrow"></span>
                            </a>
                            <ul>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-code-fork"></span>
                                        Fork
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu">
                                        <span class="sidebar-nav-item-icon fa fa-star"></span>
                                        Star
                                    </a>
                                </li>
                                <li>
                                    <a href="https://github.com/onokumus/metisMenu/issues">
                                        <span class="sidebar-nav-item-icon fa fa-exclamation-triangle"></span>
                                        Issues
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </aside>
        </div>
        <div class="col-xs-9 col-md-10">
            <div class="page-header">
                <h1>Example page header
                    <small>Subtext for header</small>
                </h1>
            </div>
        </div>
    </div>
</div>

<#--<div>-->
<#--<input type="hidden" class="admin_file" value="111.zip"/>-->
<#--</div>-->
<#--<div>-->
<#--<input type="hidden" class="admin_file" />-->
<#--</div>-->
<#--<div>-->
<#--<input type="hidden" class="admin_file"/>-->
<#--</div>-->
<#--<div>-->
<#--<input type="hidden" class="admin_file"/>-->
<#--</div>-->
<div class="container-fluid">
<#--<div class="row">-->
        <#--<button type="button" class="btn btn-success" style="margin-left: 10px;"><i class="glyphicon glyphicon-search" style="color: #ffffff;"></i>&nbsp;保存&nbsp;</button>-->
        <#--<button type="button" class="btn btn-default" style="margin-left: 10px;"><i class="glyphicon glyphicon-flash"></i>&nbsp;退出&nbsp;</button>-->
    <#--</div>-->
    <#--<br/>-->
    <#--<div class="row">-->
        <#--<form class="form-horizontal" >-->
            <#--<div class="form-group">-->
                <#--<label for="firstname" class="col-md-2 control-label">名字</label>-->
                <#--<div class="col-md-10">-->
                    <#--<input type="text" class="form-control" id="firstname"-->
                           <#--placeholder="请输入名字">-->
                <#--</div>-->
            <#--</div>-->
            <#--<div class="form-group">-->
                <#--<label for="firstname" class="col-md-2 control-label">名字1111</label>-->
                <#--<div class="col-md-10">-->
                    <#--<input type="text" class="form-control" id="firstname"-->
                           <#--placeholder="请输入名字">-->
                <#--</div>-->
            <#--</div>-->
            <#--<div class="form-group">-->
                <#--<label for="firstname" class="col-md-2 control-label">名字123123</label>-->
                <#--<div class="col-md-10">-->
                    <#--<input type="text" class="form-control" id="firstname"  placeholder="请输入名字">-->
                <#--</div>-->
            <#--</div>-->
        <#--</form>-->
    <#--</div>-->
</div>
</body>
</html>