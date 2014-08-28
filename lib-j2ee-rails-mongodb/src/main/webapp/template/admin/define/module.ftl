<!DOCTYPE html>
<html lang="zh-cn">
<body>
<div id="layout" class="easyui-layout" fit="true">
    <div data-options="region:'west',split:true" style="width:50%;">
        <table id="grid"  class="easyui-datagrid" toolbar="#toolbar" pagination="true" rownumbers="true" singleSelect="true" fit="true">
            <thead>
            <tr>
                <th field="collection" width="48%">集合</th>
                <th field="name" width="48%">名称</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="javascript:void(0)" id="add" class="easyui-linkbutton" iconCls="icon icon-add" plain="true">添加</a>
            <a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon icon-edit" plain="true" >修改</a>
            <a href="javascript:void(0)" id="delete" class="easyui-linkbutton" iconCls="icon icon-delete" plain="true">删除</a>
        </div>
        <script type="text/javascript" src="${defaultjs}"></script>
    </div>
    <div data-options="region:'center',title:'属性'">

    </div>
</div>

</body>
</html>