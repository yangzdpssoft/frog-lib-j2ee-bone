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
        <#--<input treeSearch="tree" type="text" placeholder="快速检索" style="margin: 0px;border-radius: 0px;min-height: 0px;"/>-->
        <ul id="tree" edittree class="easyui-tree" data-options="url:'${ctx}/template/admin/define/tree_data1.json'"></ul>
        <div treeContentMenu="west_tree" class="easyui-menu" style="width: 150px;">
            <div id="tree_reload" treeContextMenuButton iconCls="icon-reload">
                刷新
            </div>
            <div id="tree_drag" treeContextMenuButton iconCls="icon icon-drag">
                拖动模式
            </div>
            <div id="tree_save" treeContextMenuButton iconCls="icon-save">
                保存
            </div>
            <div class="menu-sep"></div>
            <div id="tree_rename" treeContextMenuButton iconCls="icon-edit">
                重命名
            </div>
            <div id="tree_add" treeContextMenuButton iconCls="icon-add">
                添加节点
            </div>
            <div id="tree_subadd" treeContextMenuButton iconCls="icon-add">
                添加子节点
            </div>
            <div id="tree_del" treeContextMenuButton iconCls="icon-cancel">
                删除节点
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'属性'">

    </div>
</div>
</body>
</html>