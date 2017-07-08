package com.wushengjie.service;

import com.github.pagehelper.PageHelper;
import com.wushengjie.vo.Pager;
import com.wushengjie.vo.Tag;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.wushengjie.dao.TagDao;

@Service
public class TagService{

    @Resource
    private TagDao tagDao;

    public int insert(Tag pojo){
        return tagDao.insert(pojo);
    }

    public int insertSelective(Tag pojo){
        return tagDao.insertSelective(pojo);
    }

    public int insertList(List<Tag> pojos){
        return tagDao.insertList(pojos);
    }

    public int update(Tag pojo){
        return tagDao.update(pojo);
    }

    public List<Tag> loadTag(Pager pager, String tagName){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        return tagDao.findByName(tagName);
    }

    public List<Tag> findAll(){
        return tagDao.findAll();
    }

    public void initPage(Pager pager,String tagName){
        int count = tagDao.initPage(pager,tagName);
        pager.setTotalCount(count);
    }

    public Tag findById(Integer id){
        return tagDao.findById(id);
    }

    public int deleteById(Integer id){
        return tagDao.deleteById(id);
    }
}
