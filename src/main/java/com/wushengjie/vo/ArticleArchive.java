package com.wushengjie.vo;

import java.util.List;

/**
 * Created by WU on 2017/7/14.
 * 文章归档显示vo
 */
public class ArticleArchive {

    private String month;//月份

    private List<Article> articleList;//文章信息

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
