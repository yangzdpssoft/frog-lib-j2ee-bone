<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
    <#include "/lib/include/common.ftl">
    <#include "/lib/include/easyui.ftl">
    <script type="text/javascript" src="/lib/easyui/extends/grid.js"></script>
    <script src="${defaultjs}" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
<table id="grid" grid  toolbar="#grid_toolbar" dataUrl="/factory/metaModule/list" saveUrl="/factory/metaModule/saveOrUpdate" deleteUrl="/factory/metaModule/delete" destroyUrl="" defaultValueUrl="">
    <thead>
    <tr>
        <th field="name" width="200" editor="{type:'validatebox',options:{required:true}}">名称</th>
        <th field="type" width="200" formatter="gridComboFormatter" editor="{type:'combobox', options:{editable:false,valueField:'id',textField:'text',url:'/factory/metaComboboxOption/options?name=下拉测试'}}">类型</th>
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
                <a href="javascript:void(0)" class="datagrid-btn-separator" />
            </td>
            <td>
                <a href="javascript:void(0)" id="grid_edit" datagridButton class="easyui-linkbutton" iconCls="icon icon-edit" plain="true" >编辑</a>
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
        </tr>
    </table>
</div>
<div id="gridRefDialog" data-options="toolbar: '#gridRefDialog_toolbar'"></div>
<div id="gridRefDialog_toolbar" style="padding:2px 0;display: none;">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td>
                <span style="font-size: 12px;color: #333;">&nbsp;&nbsp;编码&nbsp;&nbsp;</span>
                <input class="input"  type="text" style="width: 200px;min-height: 12px;"/>
                <span style="font-size: 12px;color: #333;">&nbsp;&nbsp;名称&nbsp;&nbsp;</span>
                <input class="input"  type="text"  style="width: 200px;min-height: 12px;"/>
                <a href="javascript:" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="margin-bottom: 2px;">查询</a>
            </td>
        </tr>
    </table>
</div>


</body>
</html>