package com.ming.ppsg.controller;

import com.ming.ppsg.service.ConfigGeneralsTypeService;
import com.ming.ppsg.service.ConfigStarService;
import com.ming.system.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ppsg/config/generalsType")
public class ConfigGeneralsTypeController {

    @Autowired
    private ConfigGeneralsTypeService configGeneralsTypeService;

    @GetMapping("/list")
    public ResultMsg list(){
        return ResultMsg.success(configGeneralsTypeService.select());
    }

}
