(function($){
    $(function(){
        $("[tip]").each(function(){
            var tip = $(this).attr('tip');
            $(this).tooltip({
                position: 'bottom',
                content: '<span>' + tip + '</span>'
            });
        });
    });
})(jQuery);
