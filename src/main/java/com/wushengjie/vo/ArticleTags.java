package com.wushengjie.vo;

/**
 * Created by wu on 2017/7/14.
 * 文章标签关联VO
 */
public class ArticleTags extends BaseVO{

    private Integer articleId;

    private Integer tagId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
