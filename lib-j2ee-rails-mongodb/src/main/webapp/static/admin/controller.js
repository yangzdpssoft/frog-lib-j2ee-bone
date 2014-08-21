app.controller('form', function ($scope, _ajax) {
    initForm();
    $('.view').fadeIn();
    _ajax.postCall('http://www.baidu.com');

    $scope.formMeta = {items: [
        {label: '用户名', field : '', type: 'text', placeholder: 'placeholder', required: true, norepeat: true, minlength: 2, maxlength: 30, pattern: '', defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'number', placeholder: 'placeholder', required: true, min: 2, max: 30, defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'password', placeholder: 'placeholder', required: true, minlength: 2, maxlength: 30, defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'select', options :[{id : '', text : '', icon : '', divider : false}], defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'groupSelect', optgroup : [{label : '', options :[{id : '', text : '', icon : ''}]}], multiple : true, maxSelect : 3, search : false, defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'statusSelect', options :[{id : '', text : '', icon : ''}], defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'tag', options :[{id : '', text : '', icon : ''}], multiple : true, maxSelect : 3, search : false, defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'switch', defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'date', startDate: '2013-01-01', endDate: '2013-12-31', minDate : '', maxDate : '', defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'time', startDate: '2013-01-01', endDate: '2013-12-31', minDate : '', maxDate : '', defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'dateRange', startDate: '2013-01-01', endDate: '2013-12-31', minDate : '', maxDate : '', defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'timeRange', startDate: '2013-01-01', endDate: '2013-12-31', minDate : '', maxDate : '', defaultValue: '', attr : [{}]},
        {label: '用户名', field : '', type: 'htmlText'},
        {label: '用户名', field : '', type: 'file'},
        {label: '用户名', field : '', type: 'image'},
        {label: '用户名', field : '', type: 'attachment'},
        {label: '用户名', field : '', type: 'hidden', value : ''},
        {label: '用户名', field : '', type: 'grid', column : [{label: '用户名', field : '', type: 'text', placeholder: 'placeholder', required: true, minlength: 2, maxlength: 30, pattern: '', defaultValue: '', attr : [{}]}]},
        {label: '用户名', field : '', type: 'easyuigrid', column : [{label: '用户名', field : '', type: 'text', placeholder: 'placeholder', required: true, minlength: 2, maxlength: 30, pattern: '', defaultValue: '', attr : [{}]}], attr : [{}]}
    ]};
    $scope.formData = {id : '', aaa : ''};
});