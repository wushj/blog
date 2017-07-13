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
@RequestMapping("/category")
@Controller
public class CategoryController {

    @Autowired
    private ArticleService articleService;

    /**
     * 根据分类加载初始化页面条数
     * @return
     */
    @RequestMapping("/initPage/{categoryId}")
    @ResponseBody
    public Pager initPage(Pager pager,@PathVariable("categoryId") Integer categoryId){
        articleService.initPageByCategoryId(pager,categoryId);
        return pager;
    }


    /**
     * 根据分类加载页面
     * @return
     */
    @RequestMapping("/load/{categoryId}")
    public String load(Pager pager,@PathVariable("categoryId") Integer categoryId, Model model){
        List<Article> articleList = articleService.findByCategoryId(pager, categoryId);
        model.addAttribute("articleList",articleList);
        return "partial/detailArticleList";
    }



}
