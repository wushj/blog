package com.wushengjie.controller;

import com.wushengjie.service.ArticleService;
import com.wushengjie.vo.ArticleArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by WU on 2017/7/19.
 * 文章归档controller
 */
@RequestMapping("/archive")
@Controller
public class ArchiveController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取文章归档数据
     * @param index
     * @return
     */
    @RequestMapping("/getArchive/{index}")
    public String getArchive(@PathVariable Integer index, Model model) {
        List<ArticleArchive> archiveList = articleService.getArchive(index);
        model.addAttribute("archiveList", archiveList);
        return "partial/archiveRow";
    }

}
