(function($){
    $(function(){
        var swfPath = ctx + '/lib/webUploader/0.1.4/Uploader.swf';
        var adminServerPath = ctx + '/admin/up';
        var frontServerPath = ctx + '/front/up';
        var adminDownloadServerPath = ctx + '/admin/download?fileName=';
        var frontDownloadServerPath = ctx + '/front/download?fileName=';
        $('.admin_image').each(function(){
            var $value = $(this);
            var $uploadDiv = $(this).parent();
            var file_up_button_id = Math.uuid();
            if($(this).val() != ''){
                $uploadDiv.append('<div id="' + file_up_button_id + '" style="margin: 10px 10px 5px 10px;"></div><div progress style="width:100%;margin: 10px;display: none;"></div><a href="'+ adminDownloadServerPath + $(this).val() + '" style="display: none;margin-left: 10px;"><img src="' + $(this).val() + '" width="100" height="100" style="border: 0px;margin-bottom: 5px;"/></a><div class="yzbutton-black" style="display: none;margin-left: 10px;margin-bottom: 5px;">撤销上传</div>');
            }else{
                $uploadDiv.append('<div id="' + file_up_button_id + '" style="margin: 10px 10px 5px 10px;"></div><div progress style="width:100%;margin: 10px;display: none;"></div><a href="javascript:" style="display: none;margin-left: 10px;"><img src="" width="200" height="200" style="border: 0px;margin-bottom: 5px;"/></a><div class="yzbutton-black" style="display: none;margin-left: 10px;margin-bottom: 5px;">撤销上传</div>');
            }
            var $file = $uploadDiv.find('a');
            var $img = $uploadDiv.find('img');
            var $progressBar = $uploadDiv.find('[progress]');
            var $clear = $uploadDiv.find('.yzbutton-black');
            var uploader = WebUploader.create({
                auto: true,
                swf: swfPath,
                server: adminServerPath,
                pick: {
                    id: '#' + file_up_button_id,
                    label: '上传图片',
                    multiple: false
                },
                accept: {
                    title: '所有图片',
                    extensions: 'gif,jpg,jpeg,bmp,png,ico,GIF,JPG,JPEG,BMP,PNG,ICO',
                    mimeTypes: 'image/*'
                },
                resize: false,
                fileNumLimit: 1
            });
            $clear.click(function(){
                $clear.fadeOut();
                $progressBar.fadeOut();
                $file.fadeOut();
                $file.attr('href','javascript:');
                $img.attr('src', '');
                $value.removeAttr('value');
            });
            uploader.on('fileQueued', function(file){
                $progressBar.fadeIn();
            });
            uploader.on('uploadProgress', function(file, percentage){
                var p = (percentage.toFixed(2) * 100).toFixed(2);
                $progressBar.progressbar('setValue', p);
            });
            uploader.on('uploadSuccess', function(file, response){
                if(isSuccess(response)){
                    $clear.fadeIn();
                    $file.fadeIn();
                    $file.attr('href', adminDownloadServerPath + response.data);
                    $img.attr('src', adminDownloadServerPath + response.data);
                    $value.val(response.data);
                }else if(isError(response) || isFailure(response)){
                    $.messager.show({title: '错误', msg:  '上传失败：' + response.data});
                }else if(isLogin(response)){
                    $.messager.show({title: '错误', msg:  '上传失败：请先登录！'});
                }
            });
            uploader.on('uploadError', function(file, reason){
                $.messager.show({title: '错误', msg:  '上传失败'});
            });
            uploader.on('uploadComplete', function(file){
                setTimeout(function(){$progressBar.fadeOut();uploader.reset();}, 1000);
            });
        });
        $('.front_image').each(function(){
            var $value = $(this);
            var $uploadDiv = $(this).parent();
            var file_up_button_id = Math.uuid();
            if($(this).val() != ''){
                $uploadDiv.append('<div id="' + file_up_button_id + '" style="margin: 10px 10px 5px 10px;"></div><div progress style="width:100%;margin: 10px;display: none;"></div><a href="'+ frontDownloadServerPath + $(this).val() + '" style="display: none;margin-left: 10px;"><img src="' + $(this).val() + '" width="100" height="100" style="border: 0px;margin-bottom: 5px;"/></a><div class="yzbutton-black" style="display: none;margin-left: 10px;margin-bottom: 5px;">撤销上传</div>');
            }else{
                $uploadDiv.append('<div id="' + file_up_button_id + '" style="margin: 10px 10px 5px 10px;"></div><div progress style="width:100%;margin: 10px;display: none;"></div><a href="javascript:" style="display: none;margin-left: 10px;"><img src="" width="200" height="200" style="border: 0px;margin-bottom: 5px;"/></a><div class="yzbutton-black" style="display: none;margin-left: 10px;margin-bottom: 5px;">撤销上传</div>');
            }
            var $file = $uploadDiv.find('a');
            var $img = $uploadDiv.find('img');
            var $progressBar = $uploadDiv.find('[progress]');
            var $clear = $uploadDiv.find('.yzbutton-black');
            var uploader = WebUploader.create({
                auto: true,
                swf: swfPath,
                server: frontServerPath,
                pick: {
                    id: '#' + file_up_button_id,
                    label: '上传图片',
                    multiple: false
                },
                accept: {
                    title: '所有图片',
                    extensions: 'gif,jpg,jpeg,bmp,png,ico,GIF,JPG,JPEG,BMP,PNG,ICO',
                    mimeTypes: 'image/*'
                },
                resize: false,
                fileNumLimit: 1
            });
            $clear.click(function(){
                $clear.fadeOut();
                $progressBar.fadeOut();
                $file.fadeOut();
                $file.attr('href','javascript:');
                $img.attr('src', '');
                $value.removeAttr('value');
            });
            uploader.on('fileQueued', function(file){
                $progressBar.fadeIn();
            });
            uploader.on('uploadProgress', function(file, percentage){
                var p = (percentage.toFixed(2) * 100).toFixed(2);
                $progressBar.progressbar('setValue', p);
            });
            uploader.on('uploadSuccess', function(file, response){
                if(isSuccess(response)){
                    $clear.fadeIn();
                    $file.fadeIn();
                    $file.attr('href', adminDownloadServerPath + response.data);
                    $img.attr('src', adminDownloadServerPath + response.data);
                    $value.val(response.data);
                }else if(isError(response) || isFailure(response)){
                    $.messager.show({title: '错误', msg:  '上传失败：' + response.data});
                }else if(isLogin(response)){
                    $.messager.show({title: '错误', msg:  '上传失败：请先登录！'});
                }
            });
            uploader.on('uploadError', function(file, reason){
                $.messager.show({title: '错误', msg:  '上传失败'});
            });
            uploader.on('uploadComplete', function(file){
                setTimeout(function(){$progressBar.fadeOut();uploader.reset();}, 1000);
            });
        });
        $('[progress]').progressbar({text : '正在上传 {value}%'});
    });
})(jQuery);
