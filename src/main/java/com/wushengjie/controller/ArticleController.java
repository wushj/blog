package com.wushengjie.controller;

import com.wushengjie.service.ArticleService;
import com.wushengjie.vo.Article;
import com.wushengjie.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by WU on 2017/7/13.
 * 文章controller
 */
@RequestMapping("/article")
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 文章列表
     * @return
     */
    @RequestMapping("articleList")
    public String articleList(){
        return "articleList";
    }

    /**
     * 文章详情
     * @param articleId
     * @param model
     * @return
     */
    @RequestMapping("/{articleId}")
    public String details(@PathVariable("articleId") Integer articleId, Model model){
        Article article = articleService.findById(articleId);
        //浏览量+1
        article.setShowCount(article.getShowCount() + 1);
        articleService.update(article);

        model.addAttribute("article",article);
        return "article";
    }

    /**
     * 首页文章初始化页面条数
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager, String title){
        articleService.initPage(pager,title,true);
        return pager;
    }

    /**
     * 首页加载文章列表
     * @param pager 分页对象
     * @param model
     * @return
     */
    @RequestMapping("/load")
    public String load(Pager pager , Model model){
        List<Article> articleList = articleService.loadIndexArticle(pager);
        model.addAttribute("articleList",articleList);
        return "partial/indexArticleList";
    }


}
