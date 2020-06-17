package com.project.sb_project.app.service.impl;

import com.project.base.exception.GlobalException;
import com.project.base.toolsUtils.ConstantUtil;
import com.project.sb_project.app.dao.UserMapper;
import com.project.sb_project.app.model.Role;
import com.project.sb_project.app.model.User;
import com.project.sb_project.app.model.UserExample;
import com.project.sb_project.app.service.RoleService;
import com.project.sb_project.app.service.UserRoleService;
import com.project.sb_project.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by PC on 2020/6/17.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insertUser(String username, String pwd){
        User user = new User();
        user.setUserName(username);
        user.setUserPwd(pwd);
        user.setUserAvailable(ConstantUtil.USER_AVAILABLE_TRUE);
        Role role = roleService.selectRoleByRoleName(ConstantUtil.USER_APP);
        if(role == null){
            throw new GlobalException(500, "不存在app用户");
        }
        int i = userMapper.insertSelective(user);
        if(i < 1){
            throw new GlobalException(500, "创建用户失败");
        }
        User u = selectUserByUsername(username);
        userRoleService.insertUserRole(u.getUserId(), role.getRoleId());
        return 1;
    }

    @Override
    public int findUserCountByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users.size();
    }

    @Override
    public User selectUserByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }
}
