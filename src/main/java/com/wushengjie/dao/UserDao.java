package com.wushengjie.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wushengjie.vo.User;


public interface UserDao {
    int insert(@Param("pojo") User pojo);

    int insertSelective(@Param("pojo") User pojo);

    int insertList(@Param("pojos") List<User> pojo);

    int update(@Param("pojo") User pojo);

    List<User> findByUserName(@Param("userName")String userName);

    User findByLoginName(@Param("loginName")String loginName);

}
