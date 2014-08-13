(function($){
    $(function(){
        var swfPath = ctx + '/lib/webUploader/0.1.4/Uploader.swf';
        var adminServerPath = ctx + '/admin/up';
        var frontServerPath = ctx + '/front/up';
        $('.admin_file').each(function(){
            var $value = $(this);
            var $uploadDiv = $(this).parent();
            var file_up_button_id = Math.uuid();
            $uploadDiv.removeClass().addClass('well');
            if($(this).val() != ''){
                $uploadDiv.append('<div id="' + file_up_button_id + '"></div>' +
                    '<h3>' +
                    '<span class="label label-success">已上传</span>&nbsp;' +
                    '<a  href="javascript:" class="btn  btn-warning" target="_blank">' + $(this).val() + '</a>' +
                    '</h3>' +
                    '<div style="display: none;" class="progress"><div  class="progress-bar progress-bar-success active"  role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div></div>');
            }else{
                $uploadDiv.append('<div id="' + file_up_button_id + '"></div>' +
                    '<h3 style="display: none;">' +
                    '<span class="label label-success">已上传</span>&nbsp;' +
                    '<a  href="javascript:" class="btn  btn-warning" target="_blank"></a>' +
                    '</h3>' +
                    '<div style="display: none;" class="progress"><div  class="progress-bar progress-bar-success active"  role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div></div>');
            }
            var $uploadStatusBar = $uploadDiv.find('h3');
            var $fileName = $uploadDiv.find('a');
            var $progressBar = $uploadDiv.find('.progress');
            var $progress = $uploadDiv.find('.progress-bar');
            var uploader = WebUploader.create({
                swf: swfPath,
                server: adminServerPath,
                pick: {
                    id: '#' + file_up_button_id,
                    label: '选择文件',
                    multiple: false
                },
                resize: false,
                fileNumLimit: 1
            });
            uploader.on('fileQueued', function(file){
                $uploadStatusBar.fadeOut();
                $progressBar.fadeIn();
                uploader.upload();
            });
            uploader.on('uploadProgress', function(file, percentage){
                var p = percentage.toFixed(2) * 100;
                $progress.attr('aria-valuenow', p);
                $progress.css('width', p + "%");
            });
            uploader.on('uploadSuccess', function(file, response){
                if(isSuccess(response)){
                    $uploadStatusBar.fadeIn();
                    $progress.text("上传成功");
                    $fileName.attr('href', ctx + response.data);
                    $fileName.text(response.data);
                    $value.val(response.data);
                }else if(isError(response) || isFailure(response)){
                    $progress.text(response.data);
                    $progress.removeClass("progress-bar-success").addClass("progress-bar-danger");
                }else if(isLogin(response)){
                    $progress.text('未登录，无法上传！');
                    $progress.removeClass("progress-bar-success").addClass("progress-bar-danger");
                }
            });
            uploader.on('uploadError', function(file, reason){
                $progress.text("上传失败");
                $progress.removeClass("progress-bar-success").addClass("progress-bar-danger");
            });
            uploader.on('uploadComplete', function(file){
                setTimeout(function(){$progressBar.fadeOut();uploader.reset();}, 1000);
            });
        });
        $('.front_file').each(function(){
            var $value = $(this);
            var $uploadDiv = $(this).parent();
            var file_up_button_id = Math.uuid();
            $uploadDiv.removeClass().addClass('well');
            if($(this).val() != ''){
                $uploadDiv.append('<div id="' + file_up_button_id + '"></div>' +
                    '<h3>' +
                    '<span class="label label-success">已上传</span>&nbsp;' +
                    '<a  href="javascript:" class="btn  btn-warning" target="_blank">' + $(this).val() + '</a>' +
                    '</h3>' +
                    '<div style="display: none;" class="progress"><div  class="progress-bar progress-bar-success active"  role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div></div>');
            }else{
                $uploadDiv.append('<div id="' + file_up_button_id + '"></div>' +
                    '<h3 style="display: none;">' +
                    '<span class="label label-success">已上传</span>&nbsp;' +
                    '<a  href="javascript:" class="btn  btn-warning" target="_blank"></a>' +
                    '</h3>' +
                    '<div style="display: none;" class="progress"><div  class="progress-bar progress-bar-success active"  role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div></div>');
            }
            var $uploadStatusBar = $uploadDiv.find('h3');
            var $fileName = $uploadDiv.find('a');
            var $progressBar = $uploadDiv.find('.progress');
            var $progress = $uploadDiv.find('.progress-bar');
            var uploader = WebUploader.create({
                swf: swfPath,
                server: frontServerPath,
                pick: {
                    id: '#' + file_up_button_id,
                    label: '选择文件',
                    multiple: false
                },
                resize: false,
                fileNumLimit: 1
            });
            uploader.on('fileQueued', function(file){
                $uploadStatusBar.fadeOut();
                $progressBar.fadeIn();
                uploader.upload();
            });
            uploader.on('uploadProgress', function(file, percentage){
                var p = percentage.toFixed(2) * 100;
                $progress.attr('aria-valuenow', p);
                $progress.css('width', p + "%");
            });
            uploader.on('uploadSuccess', function(file, response){
                if(isSuccess(response)){
                    $uploadStatusBar.fadeIn();
                    $progress.text("上传成功");
                    $fileName.attr('href', response.data);
                    $fileName.text(response.data);
                    $value.val(response.data);
                }else if(isError(response) || isFailure(response)){
                    $progress.text(response.data);
                    $progress.removeClass("progress-bar-success").addClass("progress-bar-danger");
                }else if(isLogin(response)){
                    $progress.text('未登录，无法上传！');
                    $progress.removeClass("progress-bar-success").addClass("progress-bar-danger");
                }
            });
            uploader.on('uploadError', function(file, reason){
                $progress.text("上传失败");
                $progress.removeClass("progress-bar-success").addClass("progress-bar-danger");
            });
            uploader.on('uploadComplete', function(file){
                setTimeout(function(){$progressBar.fadeOut();uploader.reset();}, 1000);
            });
        });
    });
})(jQuery);

