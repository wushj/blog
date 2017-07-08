package com.wushengjie.dao;

import com.wushengjie.vo.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wushengjie.vo.Category;


public interface CategoryDao {
    int insert(@Param("pojo") Category pojo);

    int insertSelective(@Param("pojo") Category pojo);

    int insertList(@Param("pojos") List<Category> pojo);

    int update(@Param("pojo") Category pojo);

    List<Category> findAll();

    int initPage(Pager pager,@Param("name") String name);

    List<Category> findByName(@Param("name")String name);

    Category findById(@Param("id")Integer id);

    int deleteById(@Param("id")Integer id);

}
