package com.wushengjie.dao;

import com.wushengjie.vo.ArticleTags;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ArticleTagsDao {
    int insert(@Param("pojo") ArticleTags pojo);

    int insertSelective(@Param("pojo") ArticleTags pojo);

    int insertList(@Param("pojos") List<ArticleTags> pojo);

    int update(@Param("pojo") ArticleTags pojo);
}
