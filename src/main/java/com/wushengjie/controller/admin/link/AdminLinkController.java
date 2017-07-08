package com.wushengjie.controller.admin.link;

import com.wushengjie.service.LinkService;
import com.wushengjie.util.ResultInfo;
import com.wushengjie.util.ResultInfoFactory;
import com.wushengjie.vo.Pager;
import com.wushengjie.vo.Link;
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
 * 友情链接管理Controller
 */
@Controller
@RequestMapping("/admin/link")
public class AdminLinkController {

    @Autowired
    private LinkService linkService;
    /**
     * 跳转到文章列表页面
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        return "admin/link/linkList";
    }

    /**
     * 初始化页面条数
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager,String linkName){
        linkService.initPage(pager,linkName);
        return pager;
    }

    /**
     * 加载友情链接信息列表
     * @param pager 分页对象
     * @param linkName  搜索条件
     * @param model
     * @return
     */
    @RequestMapping("/load")
    public String load(Pager pager ,String linkName,Model model){
        List<Link> linkList = linkService.loadLink(pager,linkName);
        model.addAttribute("linkList",linkList);
        return "admin/link/linkTable";
    }

    /**
     * 更新或新增友情链接
     * @return
     */
    @RequestMapping("/inertOrUpdate")
    @ResponseBody
    public ResultInfo inertOrUpdate(Link link){
        if(0 == link.getId()){
            link.setCreateTime(new Date());
            linkService.insertSelective(link);
        }else{
            Link oldLink = linkService.findById(link.getId());
            if (oldLink != null) {
                oldLink.setName(link.getName());
                linkService.update(oldLink);
            }
        }
        return ResultInfoFactory.getSuccessResultInfo("删除友情链接成功！！！");
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/addDetail")
    public String addPage(){
        return "admin/link/linkDetail";
    }

    /**
     * 跳转修改页面
     * @param id 友情链接id
     * @param model
     * @return
     */
    @RequestMapping("/editDetail/{id}")
    public String editPage(@PathVariable Integer id, Model model){
        Link link = linkService.findById(id);
        model.addAttribute("link",link);
        return "admin/link/linkDetail";
    }

    /**
     * 删除制定id的友情链接
     * @param linkId
     * @return
     */
    @RequestMapping("/deleteDetail/{linkId}")
    @ResponseBody
    public ResultInfo deleteLink(@PathVariable Integer linkId){
        int count = linkService.deleteById(linkId);
        if (count > 0){
            return ResultInfoFactory.getSuccessResultInfo("删除成功！！！");
        }
        return ResultInfoFactory.getErrorResultInfo();
    }

}
