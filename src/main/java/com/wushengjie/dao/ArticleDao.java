package com.wushengjie.dao;
import java.util.Date;

import com.wushengjie.vo.ArticleArchive;
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

    int initPageByTagId(Pager pager,@Param("tagId")Integer tagId);

    List<Article> findByTagId(@Param("tagId")Integer tagId);

    List<Article> findTop6OrderByShowCount();

    List<ArticleArchive> getArchiveMonth();

    List<Article> findByMonth(@Param("month")String month);


}
