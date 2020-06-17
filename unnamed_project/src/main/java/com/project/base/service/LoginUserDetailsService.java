package com.project.base.service;

import com.project.sb_project.app.model.Role;
import com.project.sb_project.app.model.User;
import com.project.sb_project.app.service.RoleService;
import com.project.sb_project.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2020/6/17.
 */
@Service
public class LoginUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectUserByUsername(username);
        List<Role> roleList = roleService.selectRoleByUserId(user.getUserId());
        //权限列表
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : roleList) {
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            authorityList.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPwd(), authorityList);
    }
}
