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

    /**
     * 根据名称初始化分页
     * @param pager
     * @param tagName
     */
    public void initPage(Pager pager,String tagName){
        int count = tagDao.initPage(pager,tagName);
        pager.setTotalCount(count);
    }

    /**
     * 根据名称加载分页
     * @param pager
     * @param tagName
     * @return
     */
    public List<Tag> loadTag(Pager pager, String tagName){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        return tagDao.findByName(tagName);
    }

    /**
     * 获取所有标签
     * @return
     */
    public List<Tag> findAll(){
        return tagDao.findAll();
    }

    /**
     * 根据ID获取标签
     * @param id
     * @return
     */
    public Tag findById(Integer id){
        return tagDao.findById(id);
    }

    /**
     * 根据ID删标签
     * @param id
     * @return
     */
    public int deleteById(Integer id){
        return tagDao.deleteById(id);
    }
}
