package com.ming.system.controller;

import com.ming.system.entity.User;
import com.ming.system.service.UserAuthService;
import com.ming.system.service.impl.MyUserDetailsService;
import com.ming.system.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginVerify")
    @ResponseBody
    public ResultMsg loginVerify(User user) throws AuthenticationException{
        String username = user.getUsername();
        String password = user.getPassword();
        // 登录成功会返回Token给用户
        String token = userAuthService.login(username,password);
        return ResultMsg.success(token);
    }


}
