package com.wushengjie.dao;

import com.wushengjie.vo.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wushengjie.vo.Link;


public interface LinkDao {
    int insert(@Param("pojo") Link pojo);

    int insertSelective(@Param("pojo") Link pojo);

    int insertList(@Param("pojos") List<Link> pojo);

    int update(@Param("pojo") Link pojo);

    List<Link> findAll();

    int initPage(Pager pager, @Param("name") String name);

    List<Link> findByNameOrUrl(@Param("name")String name);

    Link findById(@Param("id")Integer id);

    int deleteById(@Param("id")Integer id);
}
