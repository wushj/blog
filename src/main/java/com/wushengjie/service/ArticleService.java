package com.wushengjie.service;

import com.github.pagehelper.PageHelper;
import com.github.rjeschke.txtmark.Processor;
import com.wushengjie.dao.ArticleDao;
import com.wushengjie.vo.Article;
import com.wushengjie.vo.ArticleArchive;
import com.wushengjie.vo.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
     * 根据分类加载文章分页信息
     * @param pager
     * @param categoryId
     */
    public void initPageByTagId(Pager pager,Integer categoryId){
        int count = articleDao.initPageByTagId(pager,categoryId);
        pager.setTotalCount(count);
    }

    /**
     * 分页获取分类下文章
     */
    public List<Article> findByTagId(Pager pager,Integer categoryId){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        return articleDao.findByTagId(categoryId);
    }

    /**
     * 根据分类加载文章分页信息
     * @param pager
     * @param categoryId
     */
    public void initPageByCategoryId(Pager pager,Integer categoryId){
        int count = articleDao.initPageByCategoryId(pager,categoryId);
        pager.setTotalCount(count);
    }

    /**
     * 分页获取分类下文章
     */
    public List<Article> findByCategoryId(Pager pager,Integer categoryId){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        return articleDao.findByCategoryId(categoryId);
    }

    /**
     * 获取热门文章
     */
    public List<Article> findTop6OrderByShowCount(){
        return articleDao.findTop6OrderByShowCount();
    }
    /**
     * 更新或新增文章
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
    public List<Article> loadArticle(Pager pager, String title,Boolean isIndex){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        return articleDao.findByTitle(title,isIndex);
    }

    /**
     * 加载文章分页信息
     * @param pager
     * @param title
     */
    public void initPage(Pager pager,String title,Boolean isIndex){
        int count = articleDao.initPage(pager,title,isIndex);
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


    /**
     * 首页加载文章列表
     * @param pager
     * @return
     */
    public List<Article> loadIndexArticle(Pager pager){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        List<Article> articleList = articleDao.findAll();
        for (Article article : articleList) {
            //取第一个换行前的文本作为博客预览
            String content = String.valueOf(String.valueOf(article.getContent()).split("\n")[0]);
            //转换markdown为html
            content = Processor.process(content);
            article.setContent(content);
        }
        return articleList;
    }

    /**
     * 获取文章归档
     * @param index
     * @return
     */
    public List<ArticleArchive> getArchive(Integer index) {
        if(index == null){
            index = 0;
        }
        //每次初始2个月
        PageHelper.startPage(index,2);
        List<ArticleArchive> articleArchiveList = articleDao.getArchiveMonth();

        for (ArticleArchive archive : articleArchiveList) {
            archive.setArticleList(articleDao.findByMonth(archive.getMonth()));
        }
        return articleArchiveList;
    }
}
