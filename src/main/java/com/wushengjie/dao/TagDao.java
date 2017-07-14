package com.wushengjie.dao;

import com.wushengjie.vo.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wushengjie.vo.Tag;


public interface TagDao {
    int insert(@Param("pojo") Tag pojo);

    int insertSelective(@Param("pojo") Tag pojo);

    int insertList(@Param("pojos") List<Tag> pojo);

    int update(@Param("pojo") Tag pojo);

    List<Tag> findTop15GroupByArticle();

    List<Tag> findAll();

    int initPage(Pager pager, @Param("name") String name);

    List<Tag> findByName(@Param("name")String name);

    Tag findById(@Param("id")Integer id);

    int deleteById(@Param("id")Integer id);
}
