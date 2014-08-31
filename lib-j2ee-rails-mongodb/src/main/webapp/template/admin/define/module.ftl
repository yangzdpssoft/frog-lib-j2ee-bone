<!DOCTYPE html>
<html lang="zh-cn">
<body class="easyui-layout" fit="true">
<table id="grid" gridIndexForm  toolbar="#grid_toolbar" dataUrl="" saveUrl="" deleteUrl="" destroyUrl="" defaultValueUrl="">
    <thead>
    <tr>
        <th field="collection" width="32%" editor="{type:'validatebox',options:{required:true}}">集合</th>
        <th field="name" width="32%" editor="{type:'validatebox',options:{required:true}}">名称</th>
        <th field="type" width="32%" editor="{type:'validatebox',options:{required:true}}">管理类型</th>
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
                <a href="javascript:void(0)" id="grid_delete" datagridButton class="easyui-linkbutton" iconCls="icon icon-delete" plain="true" data-options="disabled:true">删除</a>
            </td>
            <td>
                <a href="javascript:void(0)" id="grid_destroy" datagridButton class="easyui-linkbutton" iconCls="icon icon-destroy" plain="true" tip="全部删除！" tipP = "bottom">清空</a>
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
<script type="text/javascript" src="${defaultjs}"></script>
<script type="text/javascript" src="${ctx}/lib/easyui/extends/grid.js"></script>
<script type="text/javascript" src="${ctx}/lib/base/tip.js"></script>
</body>
</html>