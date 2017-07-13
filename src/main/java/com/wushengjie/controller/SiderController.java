package com.wushengjie.controller;

import com.wushengjie.service.ArticleService;
import com.wushengjie.service.CategoryService;
import com.wushengjie.service.LinkService;
import com.wushengjie.service.TagService;
import com.wushengjie.vo.Article;
import com.wushengjie.vo.Category;
import com.wushengjie.vo.Pager;
import com.wushengjie.vo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by WU on 2017/7/13.
 * 侧边栏Controller
 */
@Controller
public class SiderController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    /**
     * 加载侧边栏
     * @param model
     * @return
     */
    @RequestMapping("/sider")
    public String load(Model model){
        //加载热门文章
        List<Article> hotArticles = articleService.findTop6OrderByShowCount();
        model.addAttribute("hotArticles",hotArticles);
        //加载分类
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        //加载标签
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags",tags);

        return "partial/sider";
    }

}
