(function($){
    $(function(){
        $("[list]").each(function(){
            var tree = $(this);
            var dependencyGridId = tree.attr("dependencyGridId");
            tree.tree({
                onSelect : function(node){
                    $(dependencyGridId).datagrid('load', {dependencyId : node.id});
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
    });
})(jQuery);
