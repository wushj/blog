package com.wushengjie.vo;

/**
 * Created by wu on 2017/7/8.
 * 友情链接实体类
 */
public class Link extends BaseVO{

    private String name;//名称

    private String url;//url

    private String desc;//描述

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
