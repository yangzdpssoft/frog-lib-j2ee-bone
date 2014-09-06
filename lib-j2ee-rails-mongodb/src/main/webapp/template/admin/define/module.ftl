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
<table id="moduleGrid" grid  toolbar="#moduleGrid_toolbar" url="${ctx}/template/admin/define/datagrid_data1.json" dataUrl="" saveUrl="" deleteUrl="" destroyUrl="" defaultValueUrl="">
    <thead>
    <tr>
        <th field="name" width="200" editor="{type:'validatebox',options:{required:true}}"><span tip="模块对应数据库哪张表！" tipP="bottom">表名</span></th>
        <th field="type" width="320" editor="{type:'ref',options:{required:true}}" formatter="gridRefFormatter">管理类型</th>
        <th field="jsPath" width="200" editor="{type:'validatebox',options:{required:true}}">脚本路径</th>
    </tr>
    </thead>
</table>
<div id="moduleGrid_toolbar">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <a href="javascript:void(0)" id="moduleGrid_save" datagridButton class="easyui-linkbutton" iconCls="icon icon-save" plain="true" data-options="disabled:true">保存</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="moduleGrid_edit" datagridButton class="easyui-linkbutton" iconCls="icon icon-edit" plain="true" >编辑</a>
            </td>
            <td>
                <a href="javascript:void(0)" class="datagrid-btn-separator" />
            </td>
            <td>
                <a href="javascript:void(0)" id="moduleGrid_add" datagridButton class="easyui-linkbutton" iconCls="icon icon-add" plain="true" data-options="disabled:true">添加</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="moduleGrid_delete" datagridButton class="easyui-linkbutton" iconCls="icon icon-delete" plain="true">删除</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="moduleGrid_selectAll" datagridButton class="easyui-linkbutton" iconCls="icon icon-selectAll" plain="true" tip="按住ctrl多选！" tipP = "bottom">全选</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="moduleGrid_insert" datagridButton class="easyui-linkbutton" iconCls="icon icon-insert" plain="true" data-options="disabled:true" tip="当前选中行上面插入一行！" tipP = "bottom">插入</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="moduleGrid_up" datagridButton class="easyui-linkbutton" iconCls="icon icon-up" plain="true" data-options="disabled:true">上移</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="moduleGrid_down" datagridButton class="easyui-linkbutton" iconCls="icon icon-down" plain="true"  data-options="disabled:true">下移</a>
            </td>
        </tr>
    </table>
</div>
<div id="gridRefDialog" data-options="toolbar: '#gridRefDialog_toolbar'"></div>
<div id="gridRefDialog_toolbar" style="padding:2px 0">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td>
                <span style="font-size: 12px;color: #333;">&nbsp;&nbsp;编码&nbsp;&nbsp;</span>
                <input  type="text" placeholder="Username" style="width: 200px;min-height: 12px;"/>
                <span style="font-size: 12px;color: #333;">&nbsp;&nbsp;名称&nbsp;&nbsp;</span>
                <input  type="text" placeholder="Username" style="width: 200px;min-height: 12px;"/>
                <a href="javascript:" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="margin-bottom: 2px;">查询</a>
            </td>
        </tr>
    </table>
</div>
</div>

</body>
</html>