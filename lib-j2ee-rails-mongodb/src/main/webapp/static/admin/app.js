//seajs
seajs.config({
    base: "./sea-modules/"
});
seajs.use('main');
//左侧导航菜单
(function($){
    $(function(){
        $('#sidebar').metisMenu({});
        $('.selectpicker').selectpicker();
        $("input:checkbox").bootstrapSwitch();
        $('.summernote').summernote({
            lang : 'zh-CN'
        });
        $('input[daterange="daterange"]').daterangepicker({
            format: 'YYYY-MM-DD',
            timePicker : true,
            timePicker12Hour : false,
            singleDatePicker : false,
            startDate : moment().subtract(100, 'days'),
            endDate : moment(),
            locale : {
                applyLabel: '确定',
                cancelLabel: '取消',
                fromLabel: '从',
                toLabel: '到',
                customRangeLabel: '自定义',
                daysOfWeek: ['日', '一', '二', '三', '四', '五','六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月',
                    '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                firstDay: 0
            },
            ranges: {
                '今天': [moment(), moment()],
                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                '近7天': [moment().subtract(6, 'days'), moment()],
                '近30天': [moment().subtract(29, 'days'), moment()],
                '这个月': [moment().startOf('month'), moment().endOf('month')],
                '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            }
        });
        $('.input-mini').attr('disabled', 'disabled');
        $('#bs-example-navbar-collapse-1 ul li').click(function(){
            if(!$(this).attr('class')){
                $('#bs-example-navbar-collapse-1 ul li').removeClass('active');
                $(this).addClass('active');
            }
        });

    });
})(jQuery);


