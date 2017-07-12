package com.wushengjie.controller;

import com.wushengjie.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wu on 2017/7/12.
 * 文章controller
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("details/{categoryId}")
    public String details(@PathVariable("categoryId") String categoryId){
//        articleService.ge
        return "details";
    }
}
