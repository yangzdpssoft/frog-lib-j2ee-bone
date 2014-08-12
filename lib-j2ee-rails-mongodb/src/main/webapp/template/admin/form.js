(function($){
    $(function(){
        $("#file-1").fileinput({
            overwriteInitial: false,
            maxFilesCount: 100
        });
        $("#upload").click(function(){
            alert(1);
        });
    });
})(jQuery);

