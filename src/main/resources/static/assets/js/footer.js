$(function(){
    //初始化页面
    initlinks();

    $(".fa-github").on('click',function(){
        window.open("https://github.com/wushj");
    });

    $(".fa-weibo").on('click',function(){
        window.open("http://weibo.com/lobotomy");
    });

});

//初始化页面
function initlinks() {
    $.ajax({
        url : '/link/load',
        success  : function(data) {
            $("#linkList").html(data);
        }
    });
}