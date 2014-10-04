<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
<#include "/lib/include/common.ftl">
<#include "/lib/include/easyui.ftl">
<#include "/lib/include/formcompoment.ftl">
<script src="${defaultjs}" type="text/javascript"></script>

</head>

<body class="easyui-layout">
<div data-options="region:'north',split:false,collapsible:false,iconCls:null" style="height:60px;">

</div>
<div data-options="region:'west',split:true, title:'菜单'" style="width:200px;">
    <div id="contentMenu" class="easyui-accordion" data-options="fit:true,border:false" style="border: 0px;">
        <div title="自动化控制台" data-options="iconCls:'icon icon-module'" style="overflow:auto;padding:10px;">
            <ul>
                <li>
                    <div>
                        <a menuid="ss" href="javascript:" url="${ctx}/admin/define/menu">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">菜单管理</span>
                        </a>
                    </div>
                </li>
                <li>
                    <div>
                        <a menuid="ss" href="javascript:" url="${ctx}/admin/define/module">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">定义模块</span>
                        </a>
                    </div>
                </li>
                <li>
                    <div>
                        <a menuid="ss" href="javascript:" url="${ctx}/admin/define/field">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">定义字段</span>
                        </a>
                    </div>
                </li>
                <li>
                    <div>
                        <a menuid="ss" href="javascript:" url="${ctx}/admin/define/tree">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">树导航</span>
                        </a>
                    </div>
                </li>
                <li>
                    <div>
                        <a menuid="ss" href="javascript:" url="${ctx}/admin/define/form">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">表单</span>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
        <div title="元数据描述" data-options="iconCls:'icon icon-module'" style="overflow:auto;padding:10px;">
            <ul>
                <li>
                    <div>
                        <a href="javascript:" url="${ctx}/factory/metaModule">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">模块</span>
                        </a>
                    </div>
                </li>
                <li>
                    <div>
                        <a href="javascript:" url="${ctx}/factory/combobox">
                            <span class="icon icon-fun">&nbsp;</span>
                            <span class="menuname">下拉框</span>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
<div data-options="region:'center', border : false">
    <div id="contentTabs" class="easyui-tabs" data-options="fit:true">
    </div>
</div>
</body>
</html>