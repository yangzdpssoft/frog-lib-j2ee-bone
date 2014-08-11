(function($){
    $(function(){
        $("#file-1").fileinput({
            overwriteInitial: false,
            maxFilesCount: 100,
            uploadUrl: '123123'

        });
        $('#file-1').fileinput('refresh', {initialPreview: ["<img src='Desert.jpg' class='file-preview-image'>", "<img src='Jellyfish.jpg' class='file-preview-image'>"]});
    });
})(jQuery);

