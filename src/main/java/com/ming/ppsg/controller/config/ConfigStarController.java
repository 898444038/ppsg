package com.ming.ppsg.controller.config;

import com.ming.ppsg.service.ConfigStarService;
import com.ming.system.annotation.Role;
import com.ming.system.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ppsg/config/star")
public class ConfigStarController {

    @Autowired
    private ConfigStarService configStarService;

    @Role
    @GetMapping("/list")
    public ResultMsg list(){
        return ResultMsg.success(configStarService.select());
    }

}
