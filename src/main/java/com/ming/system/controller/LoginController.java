package com.ming.system.controller;

import com.ming.system.entity.User;
import com.ming.system.utils.ResultMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Controller
@RequestMapping
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginVerify")
    @ResponseBody
    public ResultMsg loginVerify(User user){
        String userName = user.getUsername();
        String password = user.getPassword();

        return ResultMsg.success();
    }


}
