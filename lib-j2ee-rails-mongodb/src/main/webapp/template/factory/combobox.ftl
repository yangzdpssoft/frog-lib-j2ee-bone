<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
<#include "/lib/include/common.ftl">
<#include "/lib/include/easyui.ftl">
<script src="${ctx}/lib/easyui/extends/list.js" type="text/javascript"></script>
</head>
<body>
<div id="layout" class="easyui-layout" fit="true">
    <div id="west" data-options="region:'west',split:true" style="width:300px;">
        <ul id="list" edittree class="easyui-tree" data-options="url:'${ctx}/template/admin/define/tree_data1.json'"></ul>
        <div treeContentMenu="west_list" class="easyui-menu" style="width: 150px;">
            <div id="list_add" listContextMenuButton iconCls="icon-add">
                添加
            </div>
            <div id="list_del" listContextMenuButton iconCls="icon-cancel">
                删除
            </div>
            <div id="list_rename" listContextMenuButton iconCls="icon-edit">
                重命名
            </div>
            <div class="menu-sep"></div>
            <div id="list_reload" listContextMenuButton iconCls="icon-reload">
                刷新
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'属性'">

    </div>
</div>
</body>
</html>