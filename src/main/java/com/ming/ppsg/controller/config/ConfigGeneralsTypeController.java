package com.ming.ppsg.controller.config;

import com.ming.ppsg.service.ConfigGeneralsTypeService;
import com.ming.system.annotation.Log;
import com.ming.system.annotation.Role;
import com.ming.system.annotation.methodtype.Select;
import com.ming.system.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Log
@RestController
@RequestMapping("/ppsg/config/generalsType")
public class ConfigGeneralsTypeController {

    @Autowired
    private ConfigGeneralsTypeService configGeneralsTypeService;


    @Select
    @Role
    @PostMapping("/list")
    public ResultMsg list(){
        return ResultMsg.success(configGeneralsTypeService.select());
    }

}
