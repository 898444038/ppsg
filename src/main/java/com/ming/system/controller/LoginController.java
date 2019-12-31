package com.ming.system.controller;

import com.ming.system.entity.User;
import com.ming.system.service.impl.MyUserDetailsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "403";
    }

    @PostMapping("/auth/login")
    public String login(User user, HttpServletRequest request) throws AuthenticationException{
        String username = user.getUsername();
        String password = user.getPassword();
        // 登录成功会返回Token给用户
        String token = userDetailsService.login(username,password);
        if(StringUtils.isNotBlank(token)){
            request.setAttribute("token",token);
            return "search";
        }
        return "login";
    }

}
