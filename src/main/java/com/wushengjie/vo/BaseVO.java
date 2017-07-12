package com.wushengjie.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    private String[] proerties;//属性名

    private Map<String,Class<?>> propertiesType;//属性对象类型缓存

    //初始化
    {
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        if (props != null) {
            proerties = new String[props.length];
            propertiesType = new HashMap<String,Class<?>>();
            for (int i = 0; i < props.length; i++) {
                proerties[i] = props[i].getName();//获取bean中的属性
                Class<?> cls = props[i].getPropertyType();//获取属性的类型
                propertiesType.put(proerties[i],cls);
            }
        }
    }

    public String[] getproperties(){
        return proerties;
    }

    public Object getPropertyType(String key){
        return propertiesType.get(key);
    }

}
