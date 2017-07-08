package com.wushengjie.vo;

/**
 * Created by wu on 2017/7/8.
 * 文章分类实体类
 */
public class Category extends BaseVO{

    private String name;//名称

    private Integer sort;//排序

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
