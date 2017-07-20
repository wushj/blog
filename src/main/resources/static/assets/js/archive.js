$(function(){
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

    checkScroll();
});

var index = 1;

function lowEnough(){
    var pageHeight = Math.max(document.body.scrollHeight,document.body.offsetHeight);
    var viewportHeight = window.innerHeight ||
        document.documentElement.clientHeight ||
        document.body.clientHeight || 0;
    var scrollHeight = window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop || 0;
    return pageHeight - viewportHeight - scrollHeight < 288;
}

function loadNext() {
    $.ajax({
        url: '/archive/getArchive/' + index,
        beforeSend : function () {
            $('#spinner').show();
        },
        success: function (data) {
            $('#endlessRow').append(data);
            $('#spinner').hide();
            index++;
            pollScroll();//继续循环
        }
    });
}

function checkScroll(){
    $('#spinner').hide();
    if(!lowEnough())
        return pollScroll();
    var total = $("#total").text();
    if (index< total) {
        setTimeout(loadNext, 10);
    }

}
function pollScroll(){
    setTimeout(checkScroll,500);
}
