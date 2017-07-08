package com.wushengjie.controller.admin.category;

import com.wushengjie.service.CategoryService;
import com.wushengjie.util.ResultInfo;
import com.wushengjie.util.ResultInfoFactory;
import com.wushengjie.vo.Category;
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
 * 文章管理Controller
 */
@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 跳转到文章列表页面
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        return "admin/category/categoryList";
    }

    /**
     * 初始化页面条数
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager,String categoryName){
        categoryService.initPage(pager,categoryName);
        return pager;
    }

    /**
     * 加载分类信息列表
     * @param pager 分页对象
     * @param categoryName  搜索条件
     * @param model
     * @return
     */
    @RequestMapping("/load")
    public String load(Pager pager ,String categoryName,Model model){
        List<Category> categoryList = categoryService.loadCategory(pager,categoryName);
        model.addAttribute("categoryList",categoryList);
        return "admin/category/categoryTable";
    }

    /**
     * 更新或新增分类
     * @return
     */
    @RequestMapping("/inertOrUpdate")
    @ResponseBody
    public ResultInfo inertOrUpdate(Category category){
        if(0 == category.getId()){
            category.setCreateTime(new Date());
            categoryService.insertSelective(category);
        }else{
            Category oldCategory = categoryService.findById(category.getId());
            if (oldCategory != null) {
                oldCategory.setName(category.getName());
                oldCategory.setSort(category.getSort());
                categoryService.update(oldCategory);
            }
        }
        return ResultInfoFactory.getSuccessResultInfo("删除分类成功！！！");
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/addDetail")
    public String addPage(){
        return "admin/category/categoryDetail";
    }

    /**
     * 跳转修改页面
     * @param id 分类id
     * @param model
     * @return
     */
    @RequestMapping("/editDetail/{id}")
    public String editPage(@PathVariable Integer id, Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "admin/category/categoryDetail";
    }

    /**
     * 删除制定id的分类
     * 如果存在文章会将其移动到未分类
     * @param categoryId
     * @return
     */
    @RequestMapping("/deleteDetail/{categoryId}")
    @ResponseBody
    public ResultInfo deleteCategory(@PathVariable Integer categoryId){
        int count = categoryService.deleteById(categoryId);
        if (count > 0){
            return ResultInfoFactory.getSuccessResultInfo("删除成功！！！");
        }
        return ResultInfoFactory.getErrorResultInfo();
    }

}
