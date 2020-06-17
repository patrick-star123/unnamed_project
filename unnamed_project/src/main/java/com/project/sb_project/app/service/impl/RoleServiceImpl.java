package com.project.sb_project.app.service.impl;

import com.project.sb_project.app.dao.RoleMapper;
import com.project.sb_project.app.model.Role;
import com.project.sb_project.app.model.RoleExample;
import com.project.sb_project.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PC on 2020/6/17.
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role selectRoleByRoleName(String roleName) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andRoleNameEqualTo(roleName);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if(roles == null || roles.size() == 0){
            return null;
        }
        return roles.get(0);
    }

    @Override
    public List<Role> selectRoleByUserId(Integer userId) {
        return roleMapper.selectRoleByUserId(userId);
    }
}
