package com.wushengjie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wu on 2017/7/12.
 * 首页跳转controller
 */
@Controller
public class HomeCotroller {

    @RequestMapping(value = {"/","index"})
    public String index(){
        return "index";
    }

    @RequestMapping("aboutMe")
    public String aboutMe(){
        return "aboutMe";
    }

    @RequestMapping("archive")
    public String archive(){
        return "archive";
    }

    @RequestMapping("details")
    public String details(){
        return "details";
    }

    @RequestMapping("single")
    public String single(){
        return "single";
    }
}
