package com.wushengjie.controller.admin;

import com.wushengjie.service.UserService;
import com.wushengjie.vo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wu on 2017/7/7.
 * 后台管理Controller
 */
@Controller
public class AdminHomeController {

    @Autowired
    private UserService userService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "admin/login";
    }

    /**
     * 登陆
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    /**
     * 后台首页
     * @return
     */
    @RequestMapping("/admin/index")
    public String adminIndex(Model model){
//        String userName = (String) SecurityUtils.getSubject().getPrincipal();
//        User user = userService.findByLoginName(userName);
//        model.addAttribute("user",user);
        return "admin/index";
    }

    /**
     * 后台404
     * @return
     */
    @RequestMapping("/admin/404")
    public String error404(){
        return "admin/404";
    }

}
