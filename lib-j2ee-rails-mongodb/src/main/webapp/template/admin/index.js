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

    $(".easyui-accordion li a").click(function (event) {
        var menuname = $(this).children('.menuname').text();
        var url = $(this).attr("url");
        var icon = $(event.target).parent().children('.icon').attr("class");
        addTab(menuname, url, icon);
        $(".easyui-accordion li div").removeClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });

}

function addTab(menuname, url, icon){
    var contentTabs = '#contentTabs';
    if(!$(contentTabs).tabs('exists', menuname)){
        $(contentTabs).tabs('add',{
            title:menuname,
            href : url,
            closable:true,
            icon : icon
        });
    }else{
        $(contentTabs).tabs('select', menuname);
        $(contentTabs).tabs('update',{tab: $(contentTabs).tabs('getSelected'), options: {}});
    }
}

