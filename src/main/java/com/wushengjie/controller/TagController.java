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
@RequestMapping("/tag")
@Controller
public class TagController {

    @Autowired
    private ArticleService articleService;

    /**
     * 根据标签加载初始化页面条数
     * @return
     */
    @RequestMapping("/initPage/{tagId}")
    @ResponseBody
    public Pager initPage(Pager pager,@PathVariable("tagId") Integer tagId){
        articleService.initPageByTagId(pager,tagId);
        return pager;
    }

    /**
     * 根据标签加载页面
     * @return
     */
    @RequestMapping("/load/{tagId}")
    public String load(Pager pager,@PathVariable("tagId") Integer tagId, Model model){
        List<Article> articleList = articleService.findByTagId(pager, tagId);
        model.addAttribute("articleList",articleList);
        return "partial/detailArticleList";
    }
}
