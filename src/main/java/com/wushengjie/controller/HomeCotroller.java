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
 * 首页跳转controller
 */
@Controller
public class HomeCotroller {

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = {"/","index"})
    public String index(){
        return "index";
    }

    /**
     * 百度验证
     * @return
     */
    @RequestMapping("baidu_verify_EgpaoPoSSd")
    public String baidu_verify_EgpaoPoSSd(){
        return "baidu_verify_EgpaoPoSSd";
    }

    /**
     * 关于我
     * @return
     */
    @RequestMapping("aboutMe")
    public String aboutMe(){
        return "aboutMe";
    }

    /**
     * 文章归档
     * @return
     */
    @RequestMapping("archive")
    public String archive(){
        return "archive";
    }

    /**
     * 根据分类加载文章列表
     * @param categoryId
     * @param model
     * @return
     */
    @RequestMapping("category/{categoryId}")
    public String detailsCategory(Pager pager,@PathVariable("categoryId") Integer categoryId,Model model){
        model.addAttribute("categoryId",categoryId);
        return "detail";
    }

    /**
     * 根据标签加载文章列表
     * @param tagId
     * @param model
     * @return
     */
    @RequestMapping("tag/{tagId}")
    public String detailsTag(Pager pager,@PathVariable("tagId") Integer tagId,Model model){
        model.addAttribute("tagId",tagId);
        return "detail";
    }
}
