package com.wushengjie.controller.admin.article;

import com.alibaba.fastjson.JSON;
import com.wushengjie.service.ArticleService;
import com.wushengjie.service.CategoryService;
import com.wushengjie.service.TagService;
import com.wushengjie.util.ResultInfo;
import com.wushengjie.util.ResultInfoFactory;
import com.wushengjie.vo.Article;
import com.wushengjie.vo.Category;
import com.wushengjie.vo.Pager;
import com.wushengjie.vo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by wu on 2017/7/7.
 * 文章管理Controller
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;
    /**
     * 跳转到文章列表页面
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        return "admin/article/articleList";
    }

    /**
     * 初始化页面条数
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager,String title){
        articleService.initPage(pager,title,false);
        return pager;
    }

    /**
     * 加载文章列表
     * @param pager 分页对象
     * @param title  搜索条件
     * @param model
     * @return
     */
    @RequestMapping("/load")
    public String load(Pager pager , String title, Model model){
        List<Article> articleList = articleService.loadArticle(pager,title,false);
        model.addAttribute("articleList",articleList);
        return "admin/article/articleTable";
    }

    /**
     * 更新或新增文章
     * @return
     */
    @RequestMapping("/inertOrUpdate")
    @ResponseBody
    public ResultInfo inertOrUpdate(Article article){
        articleService.inertOrUpdate(article);
        return ResultInfoFactory.getSuccessResultInfo("保存成功！！！");
    }

    /**
     * 保存文章内容
     * @return
     */
    @RequestMapping("/updateContent")
    @ResponseBody
    public ResultInfo updateContent(Article article){
        Article oldArticle = articleService.findById(article.getId());
        oldArticle.setContent(article.getContent());
        oldArticle.setUpdateTime(new Date());
        articleService.update(oldArticle);
        return ResultInfoFactory.getSuccessResultInfo("保存成功！！！");
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/addDetail")
    public String addPage(Model model){
        Article article = new Article();
        model.addAttribute("article",article);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "admin/article/articleDetail";
    }

    /**
     * 跳转修改页面
     * @param id 文章id
     * @param model
     * @return
     */
    @RequestMapping("/editDetail/{id}")
    public String editPage(@PathVariable Integer id, Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        List<Tag> tagsList = (List<Tag>) tagService.getTagsByArticleId(id);
        String tags = JSON.toJSONString(tagsList);
        model.addAttribute("tags",tags);
        Article article = articleService.findById(id);
        model.addAttribute("article",article);
        return "admin/article/articleDetail";
    }

    /**
     * 跳转文章内容编辑页
     * @param id 文章id
     * @param model
     * @return
     */
    @RequestMapping("/editContent/{id}")
    public String editContent(@PathVariable Integer id, Model model){
        Article article = articleService.findById(id);
        model.addAttribute("article",article);
        return "admin/article/articleContent";
    }

    /**
     * 删除制定id的文章
     * @param articleId
     * @return
     */
    @RequestMapping("/deleteDetail/{articleId}")
    @ResponseBody
    public ResultInfo deleteArticle(@PathVariable Integer articleId){
        int count = articleService.deleteById(articleId);
        if (count > 0){
            return ResultInfoFactory.getSuccessResultInfo("删除成功！！！");
        }
        return ResultInfoFactory.getErrorResultInfo();
    }

}
