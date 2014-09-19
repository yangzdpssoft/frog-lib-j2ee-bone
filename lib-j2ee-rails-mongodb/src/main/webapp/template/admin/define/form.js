(function($){
    $(function(){
        $('[ueditor]').each(function(){
            var id = Math.uuid();
            $(this).attr('id', id);
            UE.getEditor(id, {
                elementPathEnabled : false
            });
        });
    });
})(jQuery);
