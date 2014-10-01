<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
    <#include "/lib/include/common.ftl">
    <#include "/lib/include/easyui.ftl">
    <link rel="stylesheet" href="${ctx}/lib/webUploader/0.1.4/webuploader.css">
    <script src="${ctx}/lib/webUploader/0.1.4/webuploader.js"></script>
    <script src="${ctx}/lib/base/upload.js" type="text/javascript"></script>
    <script src="${ctx}/lib/base/imageUpload.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/lib/easyui/extends/editgrid.js"></script>
    <!-- ueditor -->
    <script src="${ctx}/lib/ueditor/1.4.3/ueditor.config.js" type="text/javascript"></script>
    <script src="${ctx}/lib/ueditor/1.4.3/ueditor.all.js" type="text/javascript"></script>
    <script src="${defaultjs}" type="text/javascript"></script>
</head>
<body>
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
            <input type="text" class="easyui-numberbox" value="100" data-options="min:0,precision:2, width:300, groupSeparator: '，'">
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 42px;">文本</td>
        <td>
            <select class="easyui-combobox" name="dept" style="width:300px;height: 35px;" data-options="editable:false">
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
            <select class="easyui-combobox" name="dept" style="width:300px;height: 35px;" data-options="url: '${ctx}/template/admin/define/combobox_data2.json',
                method: 'get',
                valueField:'value',
                textField:'text',
                groupField:'group',editable:false">
            </select>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 42px;">文本</td>
        <td>
            <input class="easyui-datebox" style="width:300px;height: 35px;" data-options="editable:false"/>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 42px;">文本</td>
        <td>
            <input class="easyui-datetimebox" style="width:300px;height: 35px;" data-options="editable:false"/>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 60px;">文本</td>
        <td style="padding-left: 10px;">
            <div class="easyui-slider" data-options="min:10,max:90,step:10,showTip:true" style="width:290px;"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 60px;">文本</td>
        <td>
            <div>
                <input type="hidden" class="admin_file" />
            </div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 60px;">文本</td>
        <td>
            <div>
                <input type="hidden" class="admin_image" />
            </div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 60px;">文本</td>
        <td>
            <script ueditor name="content" type="text/plain" style="width : 100%;"></script>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 60px;">文本</td>
        <td>
            <script ueditor name="content" type="text/plain" style="width : 100%;"></script>
        </td>
    </tr>
    <tr>
        <td style="text-align: right;vertical-align: middle; width: 21%; padding-right: 10px;height: 60px;">文本</td>
        <td>
            <script ueditor name="content" type="text/plain" style="width : 100%;"></script>
        </td>
    </tr>
</table>
<div style="height: 500px;width: 99%;margin-left: 5px;margin-top: 5px;">
    <table id="moduleGrid" editgrid title="表单表格"  toolbar="#moduleGrid_toolbar" url="${ctx}/template/admin/define/datagrid_data1.json" dataUrl="" saveUrl="" deleteUrl="" destroyUrl="" defaultValueUrl="">
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
                    <a href="javascript:void(0)" class="datagrid-btn-separator" />
                </td>
                <td>
                    <a href="javascript:void(0)" id="moduleGrid_add" datagridButton class="easyui-linkbutton" iconCls="icon icon-add" plain="true">添加</a>
                </td>
                <td>
                    <a href="javascript:void(0)" id="moduleGrid_delete" datagridButton class="easyui-linkbutton" iconCls="icon icon-delete" plain="true">删除</a>
                </td>
                <td>
                    <a href="javascript:void(0)" id="moduleGrid_clean" datagridButton class="easyui-linkbutton" iconCls="icon icon-clean" plain="true">清空</a>
                </td>
                <td>
                    <a href="javascript:void(0)" id="moduleGrid_insert" datagridButton class="easyui-linkbutton" iconCls="icon icon-insert" plain="true" tip="当前选中行上面插入一行！" tipP = "bottom">插入</a>
                </td>
                <td>
                    <a href="javascript:void(0)" id="moduleGrid_up" datagridButton class="easyui-linkbutton" iconCls="icon icon-up" plain="true" >上移</a>
                </td>
                <td>
                    <a href="javascript:void(0)" id="moduleGrid_down" datagridButton class="easyui-linkbutton" iconCls="icon icon-down" plain="true" >下移</a>
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
                    <input class="input"  type="text" style="width: 200px;min-height: 12px;"/>
                    <span style="font-size: 12px;color: #333;">&nbsp;&nbsp;名称&nbsp;&nbsp;</span>
                    <input class="input"  type="text"  style="width: 200px;min-height: 12px;"/>
                    <a href="javascript:" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="margin-bottom: 2px;">查询</a>
                </td>
            </tr>
        </table>
    </div>
</div>


</body>
</html>