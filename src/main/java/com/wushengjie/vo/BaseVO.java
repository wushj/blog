package com.wushengjie.vo;

import java.util.Date;

/**
 * Created by wu on 2017/7/7.
 * 基类VO
 */
public class BaseVO {

    private Integer id;//id

    private Date createTime;//创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
