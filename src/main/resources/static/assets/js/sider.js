$(function(){
    //加载侧边栏
    $.ajax({
        url : '/sider',
        success  : function(data) {
            $("#sider").html(data);
        }
    });
});