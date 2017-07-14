package com.wushengjie.controller;

import com.wushengjie.service.LinkService;
import com.wushengjie.vo.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by WU on 2017/7/13.
 * 友情链接controller
 */
@RequestMapping("/link")
@Controller
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 加载友情链接
     * @return
     */
    @RequestMapping("/load")
    public String load(Model model){
        List<Link> linkList = linkService.findAll();
        model.addAttribute("linkList",linkList);
        return "partial/links";
    }

}
