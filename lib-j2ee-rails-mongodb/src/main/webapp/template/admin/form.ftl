<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include "/lib/include/common.ftl">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/lib/base/bootstrap3.chinese.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <!-- 文件上传 -->
    <link rel="stylesheet" href="${ctx}/lib/webUploader/0.1.4/webuploader.css">
    <script src="${ctx}/lib/webUploader/0.1.4/webuploader.js"></script>
    <script src="${ctx}/lib/base/upload.js" type="text/javascript"></script>
    <!-- metisMenu -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/lib/metisMenu/1.1.0/metisMenu.min.css">
    <script src="${ctx}/lib/metisMenu/1.1.0/metisMenu.min.js"></script>
    <link rel="stylesheet" href="${defaultcss}">
    <script src="${defaultjs}"></script>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">

        </div>
        <div class="col-md-10">
            <div>
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
                                <a href="#">Menu 0 <span class="fa arrow"></span></a>
                                <ul>
                                    <li><a href="#">item 0.1</a></li>
                                    <li><a href="#">item 0.2</a></li>
                                    <li><a href="http://onokumus.com">onokumus</a></li>
                                    <li><a href="#">item 0.4</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#">Menu 1 <span class="fa arrow"></span></a>
                                <ul>
                                    <li><a href="#">item 1.1</a></li>
                                    <li><a href="#">item 1.2</a></li>
                                    <li>
                                        <a href="#">Menu 1.3 <span class="fa plus-times"></span></a>
                                        <ul>
                                            <li><a href="#">item 1.3.1</a></li>
                                            <li><a href="#">item 1.3.2</a></li>
                                            <li><a href="#">item 1.3.3</a></li>
                                            <li><a href="#">item 1.3.4</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">item 1.4</a></li>
                                    <li>
                                        <a href="#">Menu 1.5 <span class="fa plus-minus"></span></a>
                                        <ul>
                                            <li><a href="#">item 1.5.1</a></li>
                                            <li><a href="#">item 1.5.2</a></li>
                                            <li><a href="#">item 1.5.3</a></li>
                                            <li><a href="#">item 1.5.4</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#">Menu 2 <span class="fa arrow"></span></a>
                                <ul>
                                    <li><a href="#">item 2.1</a></li>
                                    <li><a href="#">item 2.2</a></li>
                                    <li><a href="#">item 2.3</a></li>
                                    <li><a href="#">item 2.4</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </aside>
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