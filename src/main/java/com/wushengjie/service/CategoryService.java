package com.wushengjie.service;

import com.github.pagehelper.PageHelper;
import com.wushengjie.vo.Category;
import com.wushengjie.vo.Pager;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.wushengjie.dao.CategoryDao;

@Service
public class CategoryService{

    @Resource
    private CategoryDao categoryDao;

    public int insert(Category pojo){
        return categoryDao.insert(pojo);
    }

    public int insertSelective(Category pojo){
        return categoryDao.insertSelective(pojo);
    }

    public int insertList(List<Category> pojos){
        return categoryDao.insertList(pojos);
    }

    public List<Category> loadCategory(Pager pager, String categoryName){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        return categoryDao.findByName(categoryName);
    }

    public int update(Category pojo){
        return categoryDao.update(pojo);
    }

    public List<Category> findAll(){
        return categoryDao.findAll();
    }

    public void initPage(Pager pager,String categoryName){
        int count = categoryDao.initPage(pager,categoryName);
        pager.setTotalCount(count);
    }

    public Category findById(Integer id){
        return categoryDao.findById(id);
    }

    public int deleteById(Integer id){
        return categoryDao.deleteById(id);
    }
}
