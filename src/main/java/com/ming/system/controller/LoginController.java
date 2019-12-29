package com.ming.system.controller;

import com.ming.system.entity.User;
import com.ming.system.service.UserAuthService;
import com.ming.system.service.impl.MyUserDetailsService;
import com.ming.system.utils.ResultMsg;
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
public class LoginController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultMsg loginVerify(User user, HttpSession session) throws AuthenticationException{
        String username = user.getUsername();
        String password = user.getPassword();
        // 登录成功会返回Token给用户
        String token = userDetailsService.login(username,password);
        session.setAttribute("token",token);
        return ResultMsg.success(token);
    }

}
