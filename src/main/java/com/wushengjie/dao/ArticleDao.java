package com.wushengjie.dao;

import com.wushengjie.vo.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wushengjie.vo.Article;


public interface ArticleDao {
    int insert(@Param("pojo") Article pojo);

    int insertSelective(@Param("pojo") Article pojo);

    int insertList(@Param("pojos") List<Article> pojo);

    int update(@Param("pojo") Article pojo);

    List<Article> findAll();

    int initPage(Pager pager, @Param("title") String title,@Param("isIndex") Boolean isIndex);

    List<Article> findByTitle(@Param("title")String title,@Param("isIndex") Boolean isIndex);

    Article findById(@Param("id")Integer id);

    int deleteById(@Param("id")Integer id);

    int initPageByCategoryId(Pager pager, @Param("categoryId") Integer categoryId);

    List<Article> findByCategoryId(@Param("categoryId")Integer categoryId);

    List<Article> findTop6OrderByShowCount();

}
