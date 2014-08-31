(function($){
    $(function(){
        $("[tip]").each(function(){
            var tip = $(this).attr('tip');
            var tipPosition = $(this).attr('tipP');
            $(this).tooltip({
                position: tipPosition,
                content: '<span>' + tip + '</span>'
            });
        });
    });
})(jQuery);
