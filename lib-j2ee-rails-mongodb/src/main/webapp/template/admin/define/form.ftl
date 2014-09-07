<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
    <#include "/lib/include/common.ftl">
    <#include "/lib/include/easyui.ftl">
</head>
<body class="easyui-layout" fit="true">
<div style="margin: 20px;">
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
</div>
<table class="table">
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 42px;">文本</td>
        <td>
            <input class="input easyui-validatebox" data-options="required:true" type="text" placeholder="Username"/>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 42px;">文本</td>
        <td>
            <input class="input easyui-validatebox" data-options="validType:'email'" type="text" />
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 42px;">文本</td>
        <td>
            <input class="input easyui-validatebox" data-options="validType:'url'" type="text" />
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 42px;">文本</td>
        <td>
            <input type="text" class="input easyui-validatebox"  data-options="validType:'length[10,20]'"/>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 42px;">文本</td>
        <td>
            <select class="easyui-combobox" name="dept" style="width:300px;height: 35px;">
                <option value="aa">aitem1</option>
                <option>bitem2</option>
                <option>bitem3</option>
                <option>ditem4</option>
                <option>eitem5</option>
            </select>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 42px;">文本</td>
        <td>
            <select class="easyui-combotree" style="width:300px;height: 35px;" multiple  data-options="url:'${ctx}/template/admin/define/tree_data1.json'">
            </select>
        </td>
    </tr>
</table>
</body>
</html>