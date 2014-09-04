<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
<#include "/lib/include/common.ftl">
<#include "/lib/include/easyui.ftl">
    <script type="text/javascript" src="${ctx}/lib/easyui/extends/grid.js"></script>
    <script src="${defaultjs}" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
<table id="menuGrid" grid  toolbar="#menuGrid_toolbar" dataUrl="" saveUrl="" deleteUrl="" destroyUrl="" defaultValueUrl="">
    <thead>
    <tr>
        <th field="name" width="33%" editor="{type:'validatebox',options:{required:true}}">ff</th>
        <th field="type" width="33%" editor="{type:'validatebox',options:{required:true}}">管理类型</th>
        <th field="type" width="33%" editor="{type:'validatebox',options:{required:true}}">脚本路径</th>
    </tr>
    </thead>
</table>
<div id="menuGrid_toolbar">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <a href="javascript:void(0)" id="menuGrid_save" datagridButton class="easyui-linkbutton" iconCls="icon icon-save" plain="true" data-options="disabled:true">保存</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="menuGrid_edit" datagridButton class="easyui-linkbutton" iconCls="icon icon-edit" plain="true" >编辑</a>
            </td>
            <td>
                <a href="javascript:void(0)" class="datagrid-btn-separator" />
            </td>
            <td>
                <a href="javascript:void(0)" id="menuGrid_add" datagridButton class="easyui-linkbutton" iconCls="icon icon-add" plain="true" data-options="disabled:true">添加</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="menuGrid_delete" datagridButton class="easyui-linkbutton" iconCls="icon icon-delete" plain="true" data-options="disabled:true">删除</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="menuGrid_destroy" datagridButton class="easyui-linkbutton" iconCls="icon icon-destroy" plain="true" tip="全部删除！" tipP = "bottom">清空</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="menuGrid_insert" datagridButton class="easyui-linkbutton" iconCls="icon icon-insert" plain="true" data-options="disabled:true" tip="当前选中行上面插入一行！" tipP = "bottom">插入</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="menuGrid_up" datagridButton class="easyui-linkbutton" iconCls="icon icon-up" plain="true" data-options="disabled:true">上移</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="menuGrid_down" datagridButton class="easyui-linkbutton" iconCls="icon icon-down" plain="true"  data-options="disabled:true">下移</a>
            </td>
        </tr>
    </table>
</div>
</div>
</body>
</html>