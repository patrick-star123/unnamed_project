package com.project.sb_project.app.service;

import com.project.sb_project.app.model.Role;

import java.util.List;

/**
 * Created by PC on 2020/6/17.
 */
public interface RoleService {
    Role selectRoleByRoleName(String userApp);

    List<Role> selectRoleByUserId(Integer userId);
}
