(function($){
    $(function(){
        $("[edittree]").each(function(){
            $(this).tree({
                method : 'get',
                animate : true,
                fit : true
            });
        });
        $("[treeContentMenu]").each(function(){
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
        $("[treeContextMenuButton]").click(function(){
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
            }else if(act === 'subadd'){
                var node = tree.tree('getSelected');
                var n = {
                    id: -1,
                    text: ''
                };
                if (node){
                    tree.tree('append', {
                        parent: node.target,
                        data:n
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
            }else if(act === 'drag'){
                var drag = $(this).text().trim();
                var itemEl = $('#' + contextButton)[0];
                var item = $(this).parent().menu('getItem', itemEl);
                if(drag === '拖动模式'){
                    tree.tree('enableDnd');
                    $(this).parent().menu('setText', {
                        target: item.target,
                        text: '取消拖动'
                    });
                    $(this).parent().menu('setIcon', {
                        target: itemEl,
                        iconCls: 'icon icon-undrag'
                    });
                }else if(drag === '取消拖动'){
                    tree.tree('disableDnd');
                    $(this).parent().menu('setText', {
                        target: item.target,
                        text: '拖动模式'
                    });
                    $(this).parent().menu('setIcon', {
                        target: itemEl,
                        iconCls: 'icon icon-drag'
                    });
                }
            }else if(act === 'reload'){
                tree.tree('reload');
            }
            else if(act === 'save'){
                var roots = tree.tree('getRoots');
                var newData = [];
                for(var i = 0; i < newD.length; i++){
                    var data = tree.tree('getData', roots[i].target);
                    newData[i] = fetchProp(data, ["id", "text", "children"], "children");
                }
                var value = JSON.stringify(newData);
            }
        });
    });
})(jQuery);
