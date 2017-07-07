package com.wushengjie.config.shiro;

import com.wushengjie.vo.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wu on 2017/7/7.
 */
//@Component
public class UserRealm extends AbstractUserRealm {

    @Override
    public UserRolesAndPermissions doGetGroupAuthorizationInfo(User userInfo) {
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }

    @Override
    public UserRolesAndPermissions doGetRoleAuthorizationInfo(User userInfo) {
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }
}