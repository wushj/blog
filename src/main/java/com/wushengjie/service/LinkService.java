package com.wushengjie.service;

import com.github.pagehelper.PageHelper;
import com.wushengjie.vo.Link;
import com.wushengjie.vo.Pager;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.wushengjie.dao.LinkDao;

@Service
public class LinkService{

    @Resource
    private LinkDao linkDao;

    public int insert(Link pojo){
        return linkDao.insert(pojo);
    }

    public int insertSelective(Link pojo){
        return linkDao.insertSelective(pojo);
    }

    public int insertList(List<Link> pojos){
        return linkDao.insertList(pojos);
    }

    public int update(Link pojo){
        return linkDao.update(pojo);
    }

    /**
     * 根据名称或url初始化分页
     * @param pager
     * @param tagName
     */
    public void initPage(Pager pager,String tagName){
        int count = linkDao.initPage(pager,tagName);
        pager.setTotalCount(count);
    }

    /**
     *根据名称或url加载分页
     * @param pager
     * @param tagName
     * @return
     */
    public List<Link> loadLink(Pager pager, String tagName){
        PageHelper.offsetPage(pager.getStart(), pager.getLimit());
        return linkDao.findByNameOrUrl(tagName);
    }

    /**
     * 获取所有友情链接
     * @return
     */
    public List<Link> findAll(){
        return linkDao.findAll();
    }

    /**
     * 根据ID查询友情链接
     * @param id
     * @return
     */
    public Link findById(Integer id){
        return linkDao.findById(id);
    }

    /**
     * 根据ID删除友情链接
     * @param id
     * @return
     */
    public int deleteById(Integer id){
        return linkDao.deleteById(id);
    }
}
