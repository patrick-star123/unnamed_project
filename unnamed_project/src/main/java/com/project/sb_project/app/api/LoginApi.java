package com.project.sb_project.app.api;

import com.project.base.bean.ResponseBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PC on 2020/6/12.
 */
@RestController
@RequestMapping("/app/login")
public class LoginApi {

    @PostMapping("/register")
    public ResponseBean register(String username, String pwd){
        return null;
    }
}
