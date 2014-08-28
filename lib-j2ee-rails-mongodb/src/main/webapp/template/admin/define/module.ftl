<!DOCTYPE html>
<html lang="zh-cn">
<body>
<div id="layout" class="easyui-layout" fit="true">
    <div data-options="region:'west',split:true" style="width:60%;">
        <table id="grid" class="easyui-datagrid" idField="id" toolbar="#toolbar" pagination="false" rownumbers="true" singleSelect="true" fit="true">
            <thead>
            <tr>
                <th field="collection" width="32%" editor="{type:'validatebox',options:{required:true}}">集合</th>
                <th field="name" width="32%" editor="{type:'validatebox',options:{required:true}}">名称</th>
                <th field="type" width="32%" editor="{type:'validatebox',options:{required:true}}">管理类型</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="javascript:void(0)" id="add" class="easyui-linkbutton" iconCls="icon icon-add" plain="true">添加</a>
            <a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon icon-edit" plain="true" >修改</a>
            <a href="javascript:void(0)" id="delete" class="easyui-linkbutton" iconCls="icon icon-delete" plain="true">删除</a>
        </div>
    </div>
    <div data-options="region:'center',title:'属性'">
        <table class="table table-bordered">
            <tr>
                <td style="text-align: right;vertical-align: middle; width: 21%">文本</td>
                <td>
                    <input ng-switch-when="text" type="text" class="form-control" placeholder="Username"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;vertical-align: middle; width: 21%">文本</td>
                <td>
                    <input ng-switch-when="text" type="text" class="form-control" placeholder="Username"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;vertical-align: middle; width: 21%">文本</td>
                <td>
                    <div class="summernote"></div>
                </td>
            </tr>
        </table>
    </div>
</div>
<script type="text/javascript" src="${defaultjs}"></script>
</body>
</html>