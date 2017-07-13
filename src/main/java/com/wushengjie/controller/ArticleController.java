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
 * Created by wu on 2017/7/12.
 * 文章controller
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/{categoryId}")
    public String details(@PathVariable("categoryId") String categoryId){
        return "details";
    }

    /**
     * 初始化页面条数
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager, String title){
        articleService.initPage(pager,title);
        return pager;
    }

    /**
     * 加载文章列表
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
