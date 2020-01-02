package com.ming.system.controller;

import com.ming.system.annotation.Log;
import com.ming.system.entity.User;
import com.ming.system.service.impl.MyUserDetailsService;
import com.ming.system.utils.ResultMsg;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Controller
@RequestMapping
@Log
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

    @GetMapping("/403")
    public String fail(){
        return "403";
    }

    @GetMapping("/error")
    public String error(){
        return "403";
    }

    @PostMapping("/auth/login")
    public String login(User user, HttpServletRequest request) throws AuthenticationException{
        String username = user.getUsername();
        String password = user.getPassword();
        // 登录成功会返回Token给用户
        String token = userDetailsService.login(username,password);
        request.getSession().setAttribute("token",token);
        if(StringUtils.isNotBlank(token)){
            return "index";
        }
        return "login";
    }

    @PostMapping("/auth/register")
    public String register(User user, HttpServletRequest request) throws AuthenticationException{
        String username = user.getUsername();
        String password = user.getPassword();
        // 登录成功会返回Token给用户
        String token = userDetailsService.login(username,password);
        request.getSession().setAttribute("token",token);
        if(StringUtils.isNotBlank(token)){
            return "index";
        }
        return "login";
    }

    @PostMapping("/auth/getToken")
    @ResponseBody
    public ResultMsg login(HttpSession session) throws AuthenticationException {
        String token = (String)session.getAttribute("token");
        if(StringUtils.isNotBlank(token)){
            return ResultMsg.success(token);
        }
        return ResultMsg.failed();
    }
}
