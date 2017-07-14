package com.wushengjie.service;

import com.wushengjie.dao.ArticleTagsDao;
import com.wushengjie.vo.ArticleTags;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleTagsService{

    @Resource
    private ArticleTagsDao articleTagsDao;

    public int insert(ArticleTags pojo){
        return articleTagsDao.insert(pojo);
    }

    public int insertSelective(ArticleTags pojo){
        return articleTagsDao.insertSelective(pojo);
    }

    public int insertList(List<ArticleTags> pojos){
        return articleTagsDao.insertList(pojos);
    }

    public int update(ArticleTags pojo){
        return articleTagsDao.update(pojo);
    }
}
