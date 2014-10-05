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
        $("[grid]").each(function(){
            var dg = $(this);
            var dataUrl = dg.attr("dataUrl");
            dg.datagrid({
                url : dataUrl,
                pagination : false,
                rownumbers : true,
                singleSelect :false,
                ctrlSelect : true,
                fit:true,
                onSelect : function (rowIndex, rowData) {
                    var preSelectIndex = dg.data('preSelectIndex');
                    var edit = dg.data('edit');
                    if(edit){
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
                    }else{
                        dg.data('preSelectIndex', rowIndex);
                        dg.data('reSelectIndex', -1);
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
            dg.data('edit', false);
        });
        $("[datagridButton]").click(function(){
            var datagrid = $(this).attr('id');
            var d = datagrid.split('_');
            var grid = '#' + d[0];
            var saveUrl = $(grid).attr("saveUrl");
            var deleteUrl = $(grid).attr("deleteUrl");
            var destroyUrl = $(grid).attr("destroyUrl");
            var defaultValueUrl = $(grid).attr("defaultValueUrl");
            var act = d[1];
            if(act === 'add'){
                var edit = $(grid).data('edit');
                if(!edit){
                    return;
                }
                if(validateAllRow($(grid), 'add')){
                    $(grid).datagrid('appendRow', {id:''});
                    var currIndex = $(grid).datagrid('getRowIndex', $(grid).datagrid('getSelected'));
                    var lastRowIndex = $(grid).datagrid('getRows').length - 1;
                    $(grid).datagrid('selectRow', lastRowIndex);
                    $(grid).datagrid('endEdit', currIndex);
                    $(grid).datagrid('beginEdit', lastRowIndex);
                }
            }else if(act === 'edit'){
                var commonButton = ["save", "add", "insert", "up", "down"];
                var edit = $(grid).data('edit');
                if(edit){
                    $(grid).datagrid('options').singleSelect = false;
                    $(grid).data('edit', false);
                    $(this).linkbutton({text:'编辑'});
                    $(this).parent().parent().children().each(function(){
                        var buttonId = $(this).find('a').attr('id');
                        if(buttonId){
                            var buttonType = buttonId.split('_')[1];
                            if($.inArray(buttonType, commonButton) > -1){
                                $('#' + buttonId).linkbutton('disable');
                            }else if(buttonType === 'selectAll'){
                                $('#' + buttonId).linkbutton('enable');
                            }
                        }
                    });
                    $(grid).datagrid('rejectChanges');
                }else{
                    var preSelectIndex = $(grid).data('preSelectIndex');
                    $(grid).datagrid('unselectAll');
                    if(preSelectIndex != -1){
                        $(grid).datagrid('selectRow', preSelectIndex);
                    }
                    $(grid).datagrid('options').singleSelect = true;
                    $(grid).data('edit', true);
                    $(this).linkbutton({text:'退出编辑'});
                    var currIndex = $(grid).datagrid('getRowIndex', $(grid).datagrid('getSelected'));
                    $(grid).datagrid('beginEdit', currIndex);
                    $(this).parent().parent().children().each(function(){
                        var buttonId = $(this).find('a').attr('id');
                        if(buttonId){
                            var buttonType = buttonId.split('_')[1];
                            if($.inArray(buttonType, commonButton) > -1){
                                $('#' + buttonId).linkbutton('enable');
                            }else if(buttonType === 'selectAll'){
                                $('#' + buttonId).linkbutton('disable');
                            }
                        }
                    });
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
                var edit = $(grid).data('edit');
                if(!edit){
                    return;
                }
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
                var edit = $(grid).data('edit');
                if(!edit){
                    return;
                }
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
                var edit = $(grid).data('edit');
                if(!edit){
                    return;
                }
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
            }else if(act === 'save'){
                var edit = $(grid).data('edit');
                if(!edit){
                    return;
                }
                if(validateAllRow($(grid),'save')){
                    $(grid).datagrid('endEdit', $('#grid').data('preSelectIndex'));
                    var changeRows = $(grid).datagrid('getChanges','inserted').concat($(grid).datagrid('getChanges','updated'));
                    $.post(saveUrl, {jsonValue : JSON.stringify(changeRows)}, function(result){
                        if(isSuccess(result)){
                            alert(result.data);
                        }else{
                            dealExceptionResult(result);
                        }
                    }, 'json');
                }
            }else if(act === 'selectAll'){
                var edit = $(grid).data('edit');
                if(edit){
                    return;
                }
                var text = $(this).text().trim();
                if(text === '全选'){
                    $(this).linkbutton({iconCls:'icon icon-unselectAll', text:'取消全选'});
                    $(grid).datagrid('selectAll');
                }else if(text === '取消全选'){
                    $(this).linkbutton({iconCls:'icon icon-selectAll', text:'全选'});
                    $(grid).datagrid('unselectAll');
                }
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