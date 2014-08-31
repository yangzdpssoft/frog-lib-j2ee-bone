(function($){
    $(function(){
        $("[grid]").each(function(){
            var dg = $(this);
            dg.datagrid({
                pagination : false,
                rownumbers : true,
                singleSelect :true,
                idField : 'id',
                fit:true,
                onSelect : function (rowIndex, rowData) {
                    var preSelectIndex = dg.data('preSelectIndex');
                    if(dg.datagrid('validateRow', preSelectIndex)){
                        if (preSelectIndex != rowIndex){
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
            dg.data('edit', false);
        });
        $("[datagridButton]").click(function(){
            var datagrid = $(this).attr('id');
            var d = datagrid.split('_');
            var grid = '#' + d[0];
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
                var commonButton = ["save", "add", "delete", "insert", "up", "down"];
                var edit = $(grid).data('edit');
                if(edit){
                    $(grid).data('edit', false);
                    $(this).linkbutton({text:'编辑'});
                    $(this).parent().parent().children().each(function(){
                        var buttonId = $(this).find('a').attr('id');
                        if(buttonId){
                            var buttonType = buttonId.split('_')[1];
                            if($.inArray(buttonType, commonButton) > -1){
                                $('#' + buttonId).linkbutton('disable');
                            }else if(buttonType === 'destroy'){
                                $('#' + buttonId).linkbutton('enable');
                            }
                        }
                    });
                    $(grid).datagrid('rejectChanges');
                }else{
                    $(grid).data('edit', true);
                    $(this).linkbutton({text:'退出编辑'});
                    $(this).parent().parent().children().each(function(){
                        var buttonId = $(this).find('a').attr('id');
                        if(buttonId){
                            var buttonType = buttonId.split('_')[1];
                            if($.inArray(buttonType, commonButton) > -1){
                                $('#' + buttonId).linkbutton('enable');
                            }else if(buttonType === 'destroy'){
                                $('#' + buttonId).linkbutton('disable');
                            }
                        }
                    });
                }
                var currIndex = $(grid).datagrid('getRowIndex', $(grid).datagrid('getSelected'));
                $(grid).datagrid('beginEdit', currIndex);
            }else if(act === 'delete'){
                var edit = $(grid).data('edit');
                if(!edit){
                    return;
                }
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
            }else if(act === 'destroy'){
                var edit = $(grid).data('edit');
                if(edit){
                    return;
                }
                $.messager.confirm('请确认','确认清空所有数据，当前操作不可逆转？', function(r){
                    if(r){
                        //清空
                    }
                });
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