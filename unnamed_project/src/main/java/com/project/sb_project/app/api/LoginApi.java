package com.project.sb_project.app.api;

import com.project.base.bean.ResponseBean;
import com.project.base.exception.GlobalException;
import com.project.base.toolsUtils.ConstantUtil;
import com.project.base.toolsUtils.PasswordUtil;
import com.project.sb_project.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PC on 2020/6/12.
 */
@RestController
@RequestMapping("/app/login")
public class LoginApi {

    @Autowired
    private UserService userService;

    /**
     * app用户注册
     */
    @PostMapping("/register")
    public ResponseBean register(String username, String pwd){
        if(username == null){
            throw new GlobalException(500, "请传入密码");
        }
        if(username.length() > ConstantUtil.USERNAME_LENGHT){
            throw new GlobalException(500, "用户名过长");
        }
        //判断用户名是否重复
        int count = userService.findUserCountByUsername(username);
        if(count > 0){
            throw new GlobalException(500, "用户名已存在");
        }
        pwd = PasswordUtil.encryptionByBCrypt(pwd);
        int i = userService.insertUser(username, pwd);
        ResponseBean responseBean = new ResponseBean();
        if(i == 1){
            responseBean.setCode(200);
            responseBean.setMsg("创建用户成功");
            return responseBean;
        }
        responseBean.setCode(500);
        responseBean.setMsg("创建用户失败");
        return responseBean;
    }
}
