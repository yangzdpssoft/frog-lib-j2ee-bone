<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8" />
    <#include "/lib/include/common.ftl">
    <#include "/lib/include/easyui.ftl">
    <link href="${ctx}/template/admin/static/css/accordion.css" rel="stylesheet" />
    <script src="${defaultjs}" type="text/javascript"></script>
</head>

<body class="easyui-layout">
<div data-options="region:'north',split:false,collapsible:false,iconCls:null" style="height:100px;"></div>
<div data-options="region:'west',title:'admin',split:true,iconCls:'icon-man'" style="width:200px;">
    <div id="contentMenu" class="easyui-accordion" data-options="fit:true,border:false">
        <div title="菜单管理" data-options="iconCls:'icon icon-module'" style="overflow:auto;padding:10px;">
            <ul>
                <li>
                    <div>
                        <a menuid="ss" href="javascript:" url="${ctx}/admin/form">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">用户管理</span>
                        </a>
                    </div>
                </li>
                <li>
                    <div>
                        <a menuid="ss" href="javascript:" url="http://localhost:8080/rm">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">部门管理</span>
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