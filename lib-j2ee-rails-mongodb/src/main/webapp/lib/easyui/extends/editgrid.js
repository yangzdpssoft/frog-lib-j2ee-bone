(function($){
    $(function(){
        $.extend($.fn.datagrid.defaults.editors, {
            ref: {
                init: function(container, options){
                    var input = $('<div><input type="hidden"><input type="text" class="datagrid-editable-input" readonly="true" style="background-color: #f1f1f1;"><a gridRef href="javascript:" class="button blue">选择</a><a gridClear href="javasrcipt:" class="button white">清空</a></div>').appendTo(container);
                    $("[gridClear]").click(function(){
                        var div = $(this).parent();
                        div.find('input[type="hidden"]').val('');
                        div.find('input[type="text"]').val('');
                    });
                    $('#gridRefDialog').dialog({
                        title: '请选择',
                        width: 800,
                        height: 400,
                        closed: true,
                        cache: false,
                        modal: true,
                        href : ctx + '/admin/dialog/gridRef',
                        buttons:[{
                            iconCls:'icon-ok',
                            text:'确定',
                            handler:function(){

                            }
                        },{
                            iconCls:'icon-cancel',
                            text:'取消',
                            handler:function(){
                                $('#gridRefDialog').dialog('close');
                            }
                        }]
                    });
                    $("[gridRef]").click(function(){
                        $('#gridRefDialog').dialog('open');
                    });
                    return input;
                },
                destroy: function(target){
                   $(target).remove();
                },
                getValue: function(target){
                    var hidden = $(target).find('input[type="hidden"]').val();
                    var text = $(target).find('input[type="text"]').val();
                    if(hidden && text && hidden != '' && text != ''){
                        return  hidden + '@$##$@' + text;
                    }else{
                        return '';
                    }
                },
                setValue: function(target, value){
                    if(value && value != ''){
                        var d = value.split('@$##$@');
                        if(d.length === 2){
                            $(target).find('input[type="hidden"]').val(d[0]);
                            $(target).find('input[type="text"]').val(d[1]);
                        }else{
                            $(target).find('input[type="hidden"]').val('');
                            $(target).find('input[type="text"]').val('');
                        }
                    }else{
                        $(target).find('input[type="hidden"]').val('');
                        $(target).find('input[type="text"]').val('');
                    }
                },
                resize: function(target, width){
                    $(target)._outerWidth(width);
                    $(target).find('input[type="text"]')._outerWidth(width - 120);
                    $(target).find('a')._outerWidth(60);
                }
            }
        });
        $("[editgrid]").each(function(){
            var dg = $(this);
            dg.datagrid({
                pagination : false,
                rownumbers : true,
                singleSelect :true,
                ctrlSelect : true,
                idField : 'id',
                fit:true,
                onSelect : function (rowIndex, rowData) {
                    var preSelectIndex = dg.data('preSelectIndex');
                    if(dg.datagrid('validateRow', preSelectIndex)){
                        if (preSelectIndex != rowIndex) {
                            dg.datagrid('endEdit', preSelectIndex);
                            dg.datagrid('beginEdit', rowIndex);
                        }
                        dg.data('preSelectIndex', rowIndex);
                        dg.data('reSelectIndex', -1);
                    }else{
                        dg.data('reSelectIndex', preSelectIndex);
                    }
                },
                onClickRow : function(rowIndex, rowData){
                    var reSelectIndex = dg.data('reSelectIndex');
                    if(reSelectIndex != rowIndex && reSelectIndex != -1){
                        dg.datagrid('selectRow', reSelectIndex);
                        $.messager.show({title: '警告', msg:  '第' + (reSelectIndex + 1) + '行输入错误，无法修改其他行！'});
                    }
                }
            });
        });
        $("[datagridButton]").click(function(){
            var datagrid = $(this).attr('id');
            var d = datagrid.split('_');
            var grid = '#' + d[0];
            var act = d[1];
            if(act === 'add'){
                if(validateAllRow($(grid), 'add')){
                    $(grid).datagrid('appendRow', {id:''});
                    var currIndex = $(grid).datagrid('getRowIndex', $(grid).datagrid('getSelected'));
                    var lastRowIndex = $(grid).datagrid('getRows').length - 1;
                    $(grid).datagrid('selectRow', lastRowIndex);
                    $(grid).datagrid('endEdit', currIndex);
                    $(grid).datagrid('beginEdit', lastRowIndex);
                }
            }else if(act === 'delete'){
                var selectRow = $(grid).datagrid('getSelected');
                if(selectRow == null){
                    $.messager.alert('警告', '请选择要删除的行.', 'warning');
                    return;
                }
                $.messager.confirm('请确认','确认删除选中的行?', function(r){
                    if(r){
                        if(selectRow.id != ''){

                        }else{
                            $(grid).datagrid('deleteRow', $(grid).datagrid('getRowIndex', selectRow));
                        }
                    }
                });
            }else if(act === 'up'){
                var selectRow = $(grid).datagrid('getSelected');
                if(selectRow == null){
                    $.messager.alert('警告', '请选中一行.', 'warning');
                    return;
                }
                if(validateAllRow($(grid), 'ud')){
                    var selectIndex = $(grid).datagrid('getRowIndex', $(grid).datagrid('getSelected'));
                    if(selectIndex > 0){
                        $(grid).datagrid('endEdit', selectIndex);
                        var selectRowTemp = $.extend(true, {}, $(grid).datagrid('getSelected'));
                        var targetIndex = selectIndex - 1;
                        var targetRowTemp = $.extend(true, {}, $(grid).datagrid('getRows')[targetIndex]);
                        $(grid).datagrid('updateRow', {index : targetIndex, row : selectRowTemp});
                        $(grid).datagrid('updateRow', {index : selectIndex, row : targetRowTemp});
                        $(grid).datagrid('selectRow', targetIndex);
                        $(grid).datagrid('beginEdit', targetIndex);
                    }else{
                        $.messager.show({title: '操作警告', msg: '已经移动至第一行，无法继续上移！'});
                    }
                }
            }else if(act === 'down'){
                var selectRow = $(grid).datagrid('getSelected');
                if(selectRow == null){
                    $.messager.alert('警告', '请选中一行.', 'warning');
                    return;
                }
                if(validateAllRow($(grid), 'ud')){
                    var selectIndex = $(grid).datagrid('getRowIndex', $(grid).datagrid('getSelected'));
                    if(selectIndex < ($(grid).datagrid('getRows').length - 1)){
                        $(grid).datagrid('endEdit', selectIndex);
                        var selectRowTemp = $.extend(true, {}, $(grid).datagrid('getSelected'));
                        var targetIndex = selectIndex + 1;
                        var targetRowTemp = $.extend(true, {}, $(grid).datagrid('getRows')[targetIndex]);
                        $(grid).datagrid('updateRow', {index : targetIndex, row : selectRowTemp});
                        $(grid).datagrid('updateRow', {index : selectIndex, row : targetRowTemp});
                        $(grid).datagrid('selectRow', targetIndex);
                        $(grid).datagrid('beginEdit', targetIndex);
                    }else{
                        $.messager.show({title: '操作警告', msg: '已经移动至最后一行，无法继续下移！'});
                    }
                }
            }else if(act === 'insert'){
                var selectRow = $(grid).datagrid('getSelected');
                if(selectRow == null){
                    $.messager.alert('警告', '请选中一行.', 'warning');
                    return;
                }
                if(validateAllRow($(grid), 'insert')){
                    var selectIndex = $(grid).datagrid('getRowIndex', $(grid).datagrid('getSelected'));
                    $(grid).datagrid('endEdit', selectIndex);
                    $(grid).datagrid('insertRow',{index : selectIndex, row : {id: ''}});
                    $(grid).datagrid('selectRow', selectIndex);
                    $(grid).datagrid('beginEdit', selectIndex);
                }
            }else if(act === 'clean'){

            }
        });
    });
})(jQuery);

function validateAllRow(dg, act){
    var rows = dg.datagrid('getRows').length;
    for(var i = 0; i < rows; i++){
        if(!dg.datagrid('validateRow', i)){
            if(act == 'add'){
                $.messager.show({title: '操作警告', msg: '第' + (i + 1) + '行校验不通过，无法新增行！'});
            }else if(act == 'save'){
                $.messager.show({title: '操作警告', msg: '第' + (i + 1) + '行校验不通过，请正确填写后保存！'});
            }else if(act == 'ud'){
                $.messager.show({title: '操作警告', msg: '第' + (i + 1) + '行校验不通过，无法移动！'});
            }else if(act == 'insert'){
                $.messager.show({title: '操作警告', msg: '第' + (i + 1) + '行校验不通过，无法插入！'});
            }
            return false;
        }
    }
    return true;
}