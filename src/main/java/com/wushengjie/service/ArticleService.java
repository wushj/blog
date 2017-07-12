package com.wushengjie.service;

import com.github.pagehelper.PageHelper;
import com.wushengjie.vo.Article;
import com.wushengjie.vo.Pager;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
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

    /**
     * 更新或删除文章
     * @param article
     */
    public void inertOrUpdate(Article article){
        if(0 == article.getId()){
            article.setCreateTime(new Date());
            this.insertSelective(article);
        }else{
            article.setUpdateTime(new Date());
            this.update(article);
        }
    }

    /**
     * 根据分页加载文章
     * @param pager
     * @param title
     * @return
     */
    public List<Article> loadArticle(Pager pager, String title){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        return articleDao.findByTitle(title);
    }

    /**
     * 加载文章分页信息
     * @param pager
     * @param title
     */
    public void initPage(Pager pager,String title){
        int count = articleDao.initPage(pager,title);
        pager.setTotalCount(count);
    }

    /**
     * 根据文章ID查询文章
     * @param id
     * @return
     */
    public Article findById(Integer id){
        return articleDao.findById(id);
    }

    /**
     * 根据ID删除文章
     * @param id
     * @return
     */
    public int deleteById(Integer id){
        return articleDao.deleteById(id);
    }

}
