package com.project.base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by PC on 2020/6/24.
 */
@Target()
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginToken {
}
