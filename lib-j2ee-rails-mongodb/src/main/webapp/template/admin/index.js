(function($){
    $(function(){
        initContentMenu();
    });
})(jQuery);

function initContentMenu() {
    var contentMenu = '#contentMenu';
//    $.post('frame_ajaxLoadContentMenu.do', function(doc, status, result){
//        var menus = JSON.parse(result.responseText);
//        for (var i = 0; i < menus.length; i++) {
//            $(contentMenu).accordion('add', {title:menus[i].name, content:createDiv(menus[i].code), iconCls:menus[i].icon});
//
//        }
//    },'json');

    $(".easyui-accordion li a").click(function () {
        var menuname = $(this).children('.menuname').text();
        var url = $(this).attr("url");
        var icon = getIcon($(this).attr("menuid"));
        addTab(menuname,url,icon);
        $(".easyui-accordion li div").removeClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });

}

function addTab(menuname, url){
    var contentTabs = '#contentTabs';
    if(!$(contentTabs).tabs('exists', menuname)){
        $(contentTabs).tabs('add',{
            title:menuname,
            content:createFrame(url),
            closable:true
        });
    }else{
        $(contentTabs).tabs('select', menuname);
        $(contentTabs).tabs('update',{tab: $(contentTabs).tabs('getSelected')});
    }
}

function createDiv(id){
    return '<div id= ' + id + '></div>';
}

//获取左侧导航的图标
function getIcon(menuid){
    var icon = 'icon ';
    $.each(_menus.menus, function(i, n) {
        $.each(n.menus, function(j, o) {
            if(o.menuid==menuid){
                icon += o.icon;
            }
        });
    });
    return icon;
}

function createFrame(url)
{
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}