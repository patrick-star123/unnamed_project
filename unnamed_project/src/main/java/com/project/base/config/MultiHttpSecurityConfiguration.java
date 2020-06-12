package com.project.base.config;

import com.alibaba.fastjson.JSON;
import com.project.base.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by PC on 2020/6/10.
 * 多个HttpSecurity
 */
@Configuration
public class MultiHttpSecurityConfiguration {

    @Autowired
    private DataSource dataSource;


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
        /*@Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN","USER")
            .and()
            .withUser("appUser").password(passwordEncoder().encode("123456")).roles("USER");
        }*/

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/app/login/register");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            String userQuery = "select user_name, user_pwd, available from user where user_name = ?";
            String roleQuery = "select u.user_name, r.role_name from user u, user_role ur, role r " +
                    "where u.id=ur.user_id and ur.role_id=r.id and u.user_name=?";

            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery(userQuery)
                    .authoritiesByUsernameQuery(roleQuery);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/app/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/app/login")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.write("login app success");
                            out.flush();
                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.write("login app fail");
                            out.flush();
                        }
                    })
                    .and()
                    .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                        @Override
                        public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter writer = httpServletResponse.getWriter();
                            ResponseBean responseBean = new ResponseBean();
                            responseBean.setCode(500);
                            responseBean.setMsg("未登录APP");
                            writer.write(JSON.toJSONString(responseBean));
                            writer.flush();
                            writer.close();
                        }
                    })
                    .and()
                    .csrf().disable();
        }
    }

    @Configuration
    @Order(2)
    public class BackSecurityConfig extends WebSecurityConfigurerAdapter{

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN","USER")
            .and()
            .withUser("backUser").password(passwordEncoder().encode("123456")).roles("USER");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/back/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/back/login")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.write("login back success");
                            out.flush();
                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.write("login back fail");
                            out.flush();
                        }
                    })
                    .and()
                    .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                        @Override
                        public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter writer = httpServletResponse.getWriter();
                            ResponseBean responseBean = new ResponseBean();
                            responseBean.setCode(500);
                            responseBean.setMsg("未登录后台");
                            writer.write(JSON.toJSONString(responseBean));
                            writer.flush();
                            writer.close();
                        }
                    })
                    .and()
                    .csrf().disable();
        }
    }
}
