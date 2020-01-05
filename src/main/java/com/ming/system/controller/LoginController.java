package com.ming.system.controller;

import com.ming.system.annotation.Log;
import com.ming.system.annotation.methodtype.Login;
import com.ming.system.annotation.methodtype.Page;
import com.ming.system.annotation.methodtype.Register;
import com.ming.system.entity.User;
import com.ming.system.entity.UserInfo;
import com.ming.system.service.impl.MyUserDetailsService;
import com.ming.system.utils.ResultMsg;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Log
    @Page
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @Log
    @Page
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/login";
    }

    @Log
    @Page
    @RequestMapping("/403")
    public String fail(){
        return "403";
    }

    @Log
    @Page
    @GetMapping("/error")
    public String error(){
        return "/login";
    }

    @Log
    @Login
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

    @Log
    @Register
    @PostMapping("/auth/register")
    @ResponseBody
    public ResultMsg register(User user, HttpServletRequest request) throws AuthenticationException{
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)||StringUtils.isBlank(email)){
            return ResultMsg.failed("注册信息不能为空！","注册信息不能为空！");
        }
        int i = userDetailsService.register(user);
        if(i>0){
            return ResultMsg.success("注册成功！","注册成功！");
        }
        return ResultMsg.failed("注册失败！","注册失败！");
    }

    @Log
    @Register
    @PostMapping("/auth/register/checkUsername")
    @ResponseBody
    public ResultMsg checkUsername(String username, HttpServletRequest request) throws AuthenticationException{
        int i = userDetailsService.checkUsername(username);
        if(i>0){
            return ResultMsg.failed("用户已存在");//已存在
        }
        return ResultMsg.success();//不存在
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

    @PostMapping("/auth/getUserInfo")
    @ResponseBody
    public ResultMsg getUserInfo(HttpSession session) throws AuthenticationException {
        UserInfo info = (UserInfo)session.getAttribute("userInfo");
        if(null==info){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User)authentication.getPrincipal();
            if(null!=user){
                info = new UserInfo();
                info.setUsername(user.getUsername());
                info.setEmail(user.getEmail());
                info.setHeadPortrait(user.getHeadPortrait());
                info.setSkin(user.getSkin());
            }
        }
        if(null!=info){
            return ResultMsg.success(info);
        }
        return ResultMsg.failed();
    }

}
