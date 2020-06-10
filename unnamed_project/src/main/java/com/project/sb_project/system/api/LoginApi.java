package com.project.sb_project.system.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PC on 2020/6/9.
 */
@RestController
@RequestMapping("/system/login")
public class LoginApi {

    @GetMapping("/sysLogin")
    public String sysLogin(){
        return "登录成功";
    }
}
