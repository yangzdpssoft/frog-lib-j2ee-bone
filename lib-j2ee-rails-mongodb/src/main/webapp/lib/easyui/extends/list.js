(function($){
    $(function(){
        $("[list]").each(function(){
            $(this).tree({
                method : 'get',
                animate : true,
                fit : true
            });
        });
        $("[listContentMenu]").each(function(){
            var menu = $(this);
            var treeContentMenu = $(this).attr('treeContentMenu');
            var d = treeContentMenu.split('_');
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
                var node = tree.tree('getSelected');
                var n = {
                    id: -1,
                    text: ''
                };
                if (node){
                    tree.tree('insert', {
                        after: node.target,
                        data: n
                    });
                    var newNode =  tree.tree('find', -1);
                    tree.tree('update', {target: newNode.target, id : ''});
                    tree.tree('beginEdit', newNode.target);
                }else{
                    $.messager.show({title: '警告', msg:  '请选中一个节点！'});
                }
            }else if(act === 'rename'){
                var node = tree.tree('getSelected');
                if (node){
                    tree.tree('beginEdit', node.target);
                }else{
                    $.messager.show({title: '警告', msg:  '请选中一个节点！'});
                }
            }else if(act === 'del'){
                var node = tree.tree('getSelected');
                if (node){
                    tree.tree('remove', node.target);
                }else{
                    $.messager.show({title: '警告', msg:  '请选中一个节点！'});
                }
            }else if(act === 'reload'){
                tree.tree('reload');
            }
        });
    });
})(jQuery);
