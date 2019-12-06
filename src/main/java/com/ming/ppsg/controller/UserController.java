package com.ming.ppsg.controller;

import com.ming.ppsg.entity.User;
import com.ming.ppsg.service.UserService;
import com.ming.ppsg.utils.Operate;
import com.ming.ppsg.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/select")
    public ResultMsg select(){
        List<User> userList = userService.select();
        return ResultMsg.success(userList);
    }

    @RequestMapping("/insert")
    public ResultMsg insert(User user){
        try {
            int i = userService.insert(user);
            if(i>0){
                return ResultMsg.success();
            }
            return ResultMsg.failed();
        }catch (Exception e){
            e.printStackTrace();
            return ResultMsg.error();
        }
    }

    @RequestMapping("/update")
    public ResultMsg update(User user){
        int i = userService.update(user);
        if(i>0){
            return ResultMsg.success();
        }
        return ResultMsg.failed();
    }


    @RequestMapping("/delete/{id}")
    public ResultMsg delete(@PathVariable("id") Long id){
        int i = userService.delete(id);
        if(i>0){
            return ResultMsg.success(Operate.DELETE.getDesc());
        }
        return ResultMsg.failed();
    }

}
