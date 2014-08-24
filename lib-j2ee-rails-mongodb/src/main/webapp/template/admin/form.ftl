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
    <table id="dg" title="My Users" class="easyui-datagrid"
           url="get_users.php"
           toolbar="#toolbar" pagination="true"
           rownumbers="true" fitColumns="true" singleSelect="true" fit = "true">
        <thead>
        <tr>
            <th field="firstname" width="50">First Name</th>
            <th field="lastname" width="50">Last Name</th>
            <th field="phone" width="50">Phone</th>
            <th field="email" width="50">Email</th>
        </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
    </div>
</body>
</html>