package com.wushengjie.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wu on 2017/7/7.
 * 后台管理Controller
 */
@Controller
public class AdminHomeController {

    /**
     * 后台首页
     * @return
     */
    @RequestMapping("admin/index")
    public String index(){
        return "admin/index";
    }

    /**
     * 后台登陆页
     * @return
     */
    @RequestMapping("admin/login")
    public String login(){
        return "admin/login";
    }

}
