<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
<#include "/lib/include/common.ftl">
<#include "/lib/include/easyui.ftl">
    <script src="${defaultjs}" type="text/javascript"></script>
</head>
<body>
<div id="layout" class="easyui-layout" fit="true">
    <div id="west" data-options="region:'west',split:true" style="width:20%;">
        <input type="text" placeholder="快速检索" style="margin: 0px;border-radius: 0px;min-height: 0px;"/>
        <ul edittree class="easyui-tree" data-options="url:'${ctx}/template/admin/define/tree_data1.json'"></ul>
        <div id="westMenu" class="easyui-menu" style="width: 150px;">
            <div id="tree_refresh" iconCls="icon-reload">
                刷新
            </div>
            <div id="tree_refresh" iconCls="icon-save">
                保存
            </div>
            <div class="menu-sep"></div>
            <div id="tree_add" iconCls="icon-add">
                添加
            </div>
            <div id="tree_edit" iconCls="icon-edit">
                编辑
            </div>
            <div id="tree_del" iconCls="icon-cancel">
                删除
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'属性'">

    </div>
</div>
</body>
</html>