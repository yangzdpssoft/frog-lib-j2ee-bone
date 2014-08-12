(function($){
    $(function(){
        var uploader = WebUploader.create({

            // swf文件路径
            swf: ctx + '/lib/webUploader/0.1.4/Uploader.swf',

            // 文件接收服务端。
            server: ctx + '/admin/up',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',

            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            resize: false
        });

        $('#ctlBtn').click(function() {
           console.log(uploader.getFiles());
           uploader.upload();
        });
    });
})(jQuery);

