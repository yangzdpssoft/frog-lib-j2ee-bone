(function($){
    $(function(){
//        $(document).bind("contextmenu",function(e){
//            return true;
//        });
//        $('[gridRef]').change(function(){
//            alert($(this).val());
//        });
    });
})(jQuery);

function gridRefFormatter(value, row, index){
    var d = value.split('@$##$@');
    if(d.length === 2) {
        return d[1];
    }else{
        return value;
    }
}