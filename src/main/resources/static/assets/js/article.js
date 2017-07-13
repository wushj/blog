$(function(){
    //初始化editormd
    editormd.markdownToHTML("article-content", {
        htmlDecode      : "style,script,iframe",  // you can filter tags decode
        emoji           : true,
        taskList        : true,
        tex             : true,  // 默认不解析
        flowChart       : true,  // 默认不解析
        sequenceDiagram : true,  // 默认不解析
        tocContainer : "",
        tocDropdown   : false
    });

    //返回顶部
    $('.js-gotop').on('click', function(event){
        event.preventDefault();
        $("body,html").animate({scrollTop: 0},200);
        return false;
    });
    $(window).scroll(function(){
        var $win = $(window);
        if ($win.scrollTop() > 200) {
            $('.js-top').addClass('active');
        } else {
            $('.js-top').removeClass('active');
        }
    });
});