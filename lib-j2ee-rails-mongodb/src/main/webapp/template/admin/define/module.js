(function($){
    $(function(){
        $('#add').click(function(){
            $('#grid').datagrid('appendRow', {ID:''});
            var lastRowIndex = $('#grid').datagrid('getRows').length - 1;
            $('#grid').datagrid('selectRow', lastRowIndex);
            $('#grid').datagrid('beginEdit', lastRowIndex);
        });
    });
})(jQuery);
