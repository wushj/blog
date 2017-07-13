package com.wushengjie.controller.admin.tag;

import com.wushengjie.service.TagService;
import com.wushengjie.util.ResultInfo;
import com.wushengjie.util.ResultInfoFactory;
import com.wushengjie.vo.Tag;
import com.wushengjie.vo.Pager;
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
 * 标签管理Controller
 */
@Controller
@RequestMapping("/admin/tag")
public class AdminTagController {

    @Autowired
    private TagService tagService;
    /**
     * 跳转到文章列表页面
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        return "admin/tag/tagList";
    }

    /**
     * 初始化页面条数
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager,String tagName){
        tagService.initPage(pager,tagName);
        return pager;
    }

    /**
     * 加载标签信息列表
     * @param pager 分页对象
     * @param tagName  搜索条件
     * @param model
     * @return
     */
    @RequestMapping("/load")
    public String load(Pager pager ,String tagName,Model model){
        List<Tag> tagList = tagService.loadTag(pager,tagName);
        model.addAttribute("tagList",tagList);
        return "admin/tag/tagTable";
    }

    /**
     * 更新或新增标签
     * @return
     */
    @RequestMapping("/inertOrUpdate")
    @ResponseBody
    public ResultInfo inertOrUpdate(Tag tag){
        tagService.inertOrUpdate(tag);
        return ResultInfoFactory.getSuccessResultInfo("保存成功！！！");
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/addDetail")
    public String addPage(){
        return "admin/tag/tagDetail";
    }

    /**
     * 跳转修改页面
     * @param id 标签id
     * @param model
     * @return
     */
    @RequestMapping("/editDetail/{id}")
    public String editPage(@PathVariable Integer id, Model model){
        Tag tag = tagService.findById(id);
        model.addAttribute("tag",tag);
        return "admin/tag/tagDetail";
    }

    /**
     * 删除制定id的标签
     * @param tagId
     * @return
     */
    @RequestMapping("/deleteDetail/{tagId}")
    @ResponseBody
    public ResultInfo deleteTag(@PathVariable Integer tagId){
        int count = tagService.deleteById(tagId);
        if (count > 0){
            return ResultInfoFactory.getSuccessResultInfo("删除成功！！！");
        }
        return ResultInfoFactory.getErrorResultInfo();
    }

}
