<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8" />
    <#include "/lib/include/common.ftl">
    <#include "/lib/include/bootstrap.ftl">
    <#include "/lib/include/easyui.ftl">
    <#include "/lib/include/formcompoment.ftl">
    <link href="${ctx}/template/admin/static/css/accordion.css" rel="stylesheet" />
    <script src="${defaultjs}" type="text/javascript"></script>
</head>

<body class="easyui-layout">
<div data-options="region:'north',split:false,collapsible:false,iconCls:null" style="height:52px;">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="margin-bottom: 0px;">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img alt="Brand" src="${ctx}/template/admin/static/images/brand.png" width="30" height="30" style="margin-top: -5px;">
                    消息管理平台
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Link</a></li>
                    <li><a href="#">Link</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Link</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>
<div data-options="region:'west',split:true, title:'菜单'" style="width:200px;">
    <div id="contentMenu" class="easyui-accordion" data-options="fit:true,border:false" style="border: 0px;">
        <div title="自动化控制台" data-options="iconCls:'icon icon-module'" style="overflow:auto;padding:10px;">
            <ul>
                <li>
                    <div>
                        <a menuid="ss" href="javascript:" url="${ctx}/admin/define/module">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">定义模块</span>
                        </a>
                    </div>
                </li>
                <li>
                    <div>
                        <a menuid="ss" href="javascript:" url="http://www.baidu.com">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">百度</span>
                        </a>
                    </div>
                </li>
            </ul>
        </div>

    </div>
</div>
<div data-options="region:'center', border : false">
    <div id="contentTabs" class="easyui-tabs" data-options="fit:true">
    </div>
</div>

</body>
</html>