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
@RequestMapping("/search")
@Controller
public class SearchController {

    @Autowired
    private ArticleService articleService;

    /**
     * 根据搜索关键字加载初始化页面条数
     * @return
     */
    @RequestMapping("/initPage/{searchKey}")
    @ResponseBody
    public Pager initPage(Pager pager,@PathVariable("searchKey") String searchKey){
        articleService.initPageByKey(pager,searchKey);
        return pager;
    }

    /**
     * 根据标签加载页面
     * @return
     */
    @RequestMapping("/load/{searchKey}")
    public String load(Pager pager,@PathVariable("searchKey") String searchKey, Model model){
        List<Article> articleList = articleService.searchByKey(pager, searchKey);
        model.addAttribute("articleList",articleList);
        return "partial/detailArticleList";
    }
}
