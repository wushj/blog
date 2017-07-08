package com.wushengjie.controller.admin.article;

import com.wushengjie.service.UserService;
import com.wushengjie.vo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wu on 2017/7/7.
 * 文章管理Controller
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

    /**
     * 跳转到文章列表页面
     * @return
     */
    @RequestMapping("/list")
    public String articlePage(Model model){
//        List<Tag> tagList = tagService.getTagList();
//        List<Category> categoryList = categoryService.getCategoryList();
//        UserInfo userInfo = userService.getUserInfo();
//        model.addAttribute("userInfo",userInfo);
//        model.addAttribute("tagList",tagList);
//        model.addAttribute("categoryList",categoryList);
        return "admin/article/articleList";
    }


}
