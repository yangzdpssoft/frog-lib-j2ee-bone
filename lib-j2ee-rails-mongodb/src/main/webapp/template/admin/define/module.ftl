<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include "/lib/include/common.ftl">
    <#include "/lib/include/bootstrap.ftl">
    <#include "/lib/include/easyui.ftl">
    <#include "/lib/include/formcompoment.ftl">
</head>

<body class="easyui-layout">
    <table id="grid"  class="easyui-datagrid" toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true" fit="true">
        <thead>
        <tr>
            <th field="firstname" width="100">编码</th>
            <th field="lastname" width="100">名称</th>
        </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon icon-delete" plain="true" onclick="destroyUser()">删除</a>
    </div>
</body>
</html>