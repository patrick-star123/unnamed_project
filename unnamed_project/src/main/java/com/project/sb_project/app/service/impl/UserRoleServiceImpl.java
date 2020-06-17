package com.project.sb_project.app.service.impl;

import com.project.base.exception.GlobalException;
import com.project.sb_project.app.dao.UserRoleMapper;
import com.project.sb_project.app.model.UserRole;
import com.project.sb_project.app.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by PC on 2020/6/17.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void insertUserRole(Integer userId, Integer roleId) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        int i = userRoleMapper.insertSelective(userRole);
        if(i < 1){
            throw new GlobalException(500, "用户关联角色失败");
        }
    }
}
