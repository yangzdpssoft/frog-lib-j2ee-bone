<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
<#include "/lib/include/common.ftl">
<#include "/lib/include/easyui.ftl">
    <script src="${ctx}/lib/easyui/extends/list.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/lib/easyui/extends/listgrid.js"></script>
    <script src="${defaultjs}" type="text/javascript"></script>
</head>
<body>
<div id="layout" class="easyui-layout" fit="true">
    <div id="west" data-options="region:'west',split:true" style="width:300px;">
        <ul id="list" list class="easyui-tree" data-options="url:'${ctx}/factory/metaCombobox/list'" saveUrl="${ctx}/factory/metaCombobox/saveOrUpdate" deleteUrl="${ctx}/factory/metaCombobox/delete" dependencyGridId="#grid"></ul>
        <div listContentMenu="west_list" class="easyui-menu" style="width: 150px;">
            <div id="list_add" listContextMenuButton iconCls="icon-add">
                添加
            </div>
            <div id="list_del" listContextMenuButton iconCls="icon-cancel">
                删除
            </div>
            <div id="list_rename" listContextMenuButton iconCls="icon-edit">
                重命名
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'选择配置'">
        <table id="grid" grid  toolbar="#grid_toolbar" dataUrl="${ctx}/factory/metaComboboxOption/list" saveUrl="${ctx}/factory/metaComboboxOption/saveOrUpdate" deleteUrl="${ctx}/factory/metaComboboxOption/delete" destroyUrl="" defaultValueUrl="" dependencyTreeId="#list">
            <thead>
            <tr>
                <th field="name" width="250" editor="{type:'validatebox',options:{required:true}}">选项</th>
                <th field="group" width="250" editor="{type:'validatebox'}">分组</th>
            </tr>
            </thead>
        </table>
        <div id="grid_toolbar">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <a href="javascript:void(0)" id="grid_save" datagridButton class="easyui-linkbutton" iconCls="icon icon-save" plain="true" data-options="disabled:true">保存</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="grid_edit" datagridButton class="easyui-linkbutton" iconCls="icon icon-edit" plain="true" >编辑</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" class="datagrid-btn-separator" />
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="grid_add" datagridButton class="easyui-linkbutton" iconCls="icon icon-add" plain="true" data-options="disabled:true">添加</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="grid_delete" datagridButton class="easyui-linkbutton" iconCls="icon icon-delete" plain="true">删除</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="grid_selectAll" datagridButton class="easyui-linkbutton" iconCls="icon icon-selectAll" plain="true" tip="按住ctrl多选！" tipP = "bottom">全选</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="grid_insert" datagridButton class="easyui-linkbutton" iconCls="icon icon-insert" plain="true" data-options="disabled:true" tip="当前选中行上面插入一行！" tipP = "bottom">插入</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="grid_up" datagridButton class="easyui-linkbutton" iconCls="icon icon-up" plain="true" data-options="disabled:true">上移</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="grid_down" datagridButton class="easyui-linkbutton" iconCls="icon icon-down" plain="true"  data-options="disabled:true">下移</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>