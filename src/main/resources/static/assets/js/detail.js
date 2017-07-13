$(function(){
    //初始化页面
    initPage();

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

    //初始化页面
function initPage() {
    var categoryId = $("#categoryId").text();
    var page = $("#current-page").text();
    if (page==null || page ==0 ){
        page = 1;
    }
    $.ajax({
        url : '/category/initPage/' + categoryId,
        data : 'page='+page,
        success  : function(data) {
            $("#total-num").text(data.totalCount);
            $("#total-page").text(data.totalPageNum);
            $("#current-page").text(data.page);
            if (data.totalCount > 0) {
                $.jqPaginator('#pagination', {
                    totalPages: data.totalPageNum,
                    visiblePages: 5,
                    currentPage: data.page,
                    prev: '<li class="prev"><a href="#" class="older">Newer</i></a></li>',
                    next: '<li class="next"><a href="#" class="newer">Older</i></a></li>',
                    page: '<li class="page"><a href="#" class="newer">{{page}}</i></a></li>',
                    onPageChange: function (num, type) {
                        // 加载分类列表
                        $("#current-page").text(num);
                        loadCategoryArticleList();
                    }
                });
            }else {
                loadCategoryArticleList();
            }
        }
    });
}

    // 加载分类列表
function loadCategoryArticleList(){
    // 收集参数
    var categoryId = $("#categoryId").text();
    var page = $("#current-page").text();
    if(isEmpty(page) || page == 0){
        page = 1;
    }
    // 查询列表
    $.ajax({
        url : '/category/load/' + categoryId,
        data : 'page='+page,
        success  : function(data) {
            $("#dataList").html(data);
        }
    });
}