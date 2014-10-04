(function($){
    $(function(){
        $("[list]").each(function(){
            var saveUrl = $(this).attr("saveUrl");
            var tree = $(this);
            tree.tree({
                method : 'get',
                animate : true,
                fit : true,
                dnd : false,
                onAfterEdit:function(node){
                    $.post(saveUrl, {id : node.id, text : node.text}, function(result){
                        if(isSuccess(result)){
                            tree.tree('update', {target: node.target, id : result.data.id, text: result.data.text});
                            tree.tree('select', node.target);
                        }else{
                            dealExceptionResult(result);
                        }
                    }, 'json');
                }
            });
        });
        $("[listContentMenu]").each(function(){
            var menu = $(this);
            var listContentMenu = $(this).attr('listContentMenu');
            var d = listContentMenu.split('_');
            menu.bind('contextmenu', function(e){
                return false;
            });
            $.each(d, function(){
                $('#' + this).bind('contextmenu',function(e){
                    menu.menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                    return false;
                });
            });
        });
        $("[listContextMenuButton]").click(function(){
            var contextButton = $(this).attr('id');
            var d = contextButton.split('_');
            var tree = $('#' + d[0]);
            var act = d[1];
            if(act === 'add'){
                tree.tree('append', {parent:null, data:[{id : null, text : ''}]});
                var roots = tree.tree('getRoots');
                var lastNode = roots[roots.length - 1];
                tree.tree('select', lastNode.target);
                tree.tree('beginEdit', lastNode.target);
            }else if(act === 'rename'){
                var node = tree.tree('getSelected');
                if (node){
                    tree.tree('beginEdit', node.target);
                }else{
                    $.messager.show({title: '警告', msg:  '请选中一个节点！'});
                }
            }else if(act === 'del'){
                var node = tree.tree('getSelected');
                var deleteUrl = tree.attr("deleteUrl");
                if (node){
                    $.post(deleteUrl, {id : node.id}, function(result){
                        if(isSuccess(result)){
                            tree.tree('remove', node.target);
                        }else{
                            dealExceptionResult(result);
                        }
                    }, 'json');
                }else{
                    $.messager.show({title: '警告', msg:  '请选中一个节点！'});
                }
            }
        });
    });
})(jQuery);
