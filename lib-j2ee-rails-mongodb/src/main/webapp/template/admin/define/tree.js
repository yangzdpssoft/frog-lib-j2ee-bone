(function($){
    $(function(){
        $("[edittree]").each(function(){
            $(this).tree({
                method : 'get',
                animate : true,
                fit : true
            });
        });
        $("#west").bind('contextmenu',function(e){
            $('#westMenu').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
            return false;
        });
        $("[edittree]").bind('contextmenu',function(e){
            $('#westMenu').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
            return false;
        });
        $("#westMenu").bind('contextmenu',function(e){
            return false;
        });
    });
})(jQuery);
