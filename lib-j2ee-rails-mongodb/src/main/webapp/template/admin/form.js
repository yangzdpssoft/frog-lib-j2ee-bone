(function($){
    $(function(){
        $("#file-1").fileinput({
            overwriteInitial: false,
            maxFileSize: 1000 * 10,
            maxFilesCount: 100

        });
        $('#file-1').fileinput('refresh', {initialPreview: ["<img src='Desert.jpg' class='file-preview-image'>", "<img src='Jellyfish.jpg' class='file-preview-image'>"]});
    });
})(jQuery);

