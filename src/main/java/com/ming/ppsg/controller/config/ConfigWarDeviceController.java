package com.ming.ppsg.controller.config;

import com.ming.ppsg.service.ConfigWarDeviceService;
import com.ming.system.annotation.Log;
import com.ming.system.annotation.methodtype.Select;
import com.ming.system.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Log
@RestController
@RequestMapping("/ppsg/config/warDevice")
public class ConfigWarDeviceController {

    @Autowired
    private ConfigWarDeviceService configWarDeviceService;

    @Select
    @GetMapping("/list")
    public ResultMsg list(){
        return ResultMsg.success(configWarDeviceService.select());
    }

}
