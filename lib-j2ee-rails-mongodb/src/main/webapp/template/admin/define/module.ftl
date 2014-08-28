<!DOCTYPE html>
<html lang="zh-cn">
<body class="easyui-layout">
    <table id="grid"  class="easyui-datagrid" toolbar="#toolbar" pagination="true" fitColumns="true" rownumbers="true" singleSelect="true" fit="true">
        <thead>
        <tr>
            <th field="collection" width="100">集合</th>
            <th field="name" width="100">名称</th>
            <th field="saveOrUpdate" width="100">saveOrUpdate</th>
            <th field="update" width="100">update</th>
            <th field="delete" width="100">delete</th>
            <th field="list" width="100">list</th>
            <th field="get" width="100">get</th>
            <th field="get" width="100">moduleJS</th>
        </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" id="add" class="easyui-linkbutton" iconCls="icon icon-add" plain="true">添加</a>
        <a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon icon-edit" plain="true" >修改</a>
        <a href="javascript:void(0)" id="delete" class="easyui-linkbutton" iconCls="icon icon-delete" plain="true">删除</a>
    </div>
<script type="text/javascript" src="${defaultjs}"></script>
</body>
</html>