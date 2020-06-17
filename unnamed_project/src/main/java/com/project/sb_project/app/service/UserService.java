package com.project.sb_project.app.service;


import com.project.sb_project.app.model.User;

/**
 * Created by PC on 2020/6/17.
 */
public interface UserService {

    int insertUser(String username, String pwd);

    int findUserCountByUsername(String username);

    User selectUserByUsername(String username);
}
