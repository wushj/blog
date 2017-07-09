package com.wushengjie.service;

import com.github.pagehelper.PageHelper;
import com.wushengjie.vo.Article;
import com.wushengjie.vo.Pager;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.wushengjie.dao.ArticleDao;

@Service
public class ArticleService{

    @Resource
    private ArticleDao articleDao;

    public int insert(Article pojo){
        return articleDao.insert(pojo);
    }

    public int insertSelective(Article pojo){
        return articleDao.insertSelective(pojo);
    }

    public int insertList(List<Article> pojos){
        return articleDao.insertList(pojos);
    }

    public int update(Article pojo){
        return articleDao.update(pojo);
    }

    public List<Article> loadArticle(Pager pager, String title){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        return articleDao.findByTitle(title);
    }

    public List<Article> findAll(){
        return articleDao.findAll();
    }

    public void initPage(Pager pager,String title){
        int count = articleDao.initPage(pager,title);
        pager.setTotalCount(count);
    }

    public Article findById(Integer id){
        return articleDao.findById(id);
    }

    public int deleteById(Integer id){
        return articleDao.deleteById(id);
    }
}
