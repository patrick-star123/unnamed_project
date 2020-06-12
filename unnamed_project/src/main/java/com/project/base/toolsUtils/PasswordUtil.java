package com.project.base.toolsUtils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by PC on 2020/6/12.
 */
public class PasswordUtil {

    public static String encryptionByBCrypt(String pwd){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pwd);
    }

}
