(function($){
    $(function(){
        $('.webuploader_file').parent().removeClass().addClass('well');
//        $('.webuploader_file').parent().html('<div class="file_up_button">选择文件</div><h3 id="uploadStatusbar" style="display: none;"></h3>');
        var uploader = WebUploader.create({

            // swf文件路径
            swf: ctx + '/lib/webUploader/0.1.4/Uploader.swf',

            // 文件接收服务端。
            server: ctx + '/admin/up',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '.file_up_button',

            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            resize: false
        });

        uploader.on('fileQueued', function(file){
            $('#uploadStatusbar').fadeIn();
            $('#fileName').text(file.name);
            $('#progressbar').fadeIn();
            uploader.upload();
        });
        uploader.on('uploadProgress', function(file, percentage){
            var p = percentage.toFixed(2) * 100;
            $('#progress').attr('aria-valuenow', p);
            $('#progress').css('width', p + "%");
        });
        uploader.on('uploadSuccess', function(file){
            $('#uploadStatus').fadeIn();
            $('#progress').text("上传成功");
        });
        uploader.on('uploadError', function(file){
            $('#progress').text("上传失败");
            $("#progress").removeClass("progress-bar-success").addClass("progress-bar-danger");
        });
        uploader.on('uploadComplete', function(file){
            setTimeout(function(){$('#progressbar').fadeOut();}, 2000);
        });
    });
})(jQuery);

