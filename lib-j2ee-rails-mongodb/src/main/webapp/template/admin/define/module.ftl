<!DOCTYPE html>
<html lang="zh-cn">
<body>
<div id="layout" class="easyui-layout" fit="true">
    <div data-options="region:'west',split:true" style="width:60%;">
        <table id="grid" edatagrid idField="id" toolbar="#toolbar" pagination="false" rownumbers="true" singleSelect="true" fit="true">
            <thead>
            <tr>
                <th field="collection" width="32%" editor="{type:'validatebox',options:{required:true}}">集合</th>
                <th field="name" width="32%" editor="{type:'validatebox',options:{required:true}}">名称</th>
                <th field="type" width="32%" editor="{type:'validatebox',options:{required:true}}">管理类型</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <a href="javascript:void(0)" id="save" datagridButton="#grid" class="easyui-linkbutton" iconCls="icon icon-save" plain="true">保存</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" class="datagrid-btn-separator" />
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="add" datagridButton="#grid" class="easyui-linkbutton" iconCls="icon icon-add" plain="true">添加</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="edit" datagridButton="#grid" class="easyui-linkbutton" iconCls="icon icon-edit" plain="true" >修改</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="delete" datagridButton="#grid" class="easyui-linkbutton" iconCls="icon icon-delete" plain="true">删除</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="insert" datagridButton="#grid" class="easyui-linkbutton" iconCls="icon icon-insert" plain="true">插入</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="up" datagridButton="#grid" class="easyui-linkbutton" iconCls="icon icon-up" plain="true">上移</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" id="down" datagridButton="#grid" class="easyui-linkbutton" iconCls="icon icon-down" plain="true">下移</a>
                    </td>
                </tr>
            </table>

        </div>
    </div>
    <div data-options="region:'center',title:'属性'">
        <table class="table">
            <tr>
                <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;">文本</td>
                <td>
                    <input  type="text" placeholder="Username"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;">文本</td>
                <td>
                    <input type="text" placeholder="Username"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;">文本</td>
                <td>
                    <input type="text" placeholder="Username"/>
                </td>
            </tr>
        </table>
    </div>
</div>
<script type="text/javascript" src="${defaultjs}"></script>
</body>
</html>