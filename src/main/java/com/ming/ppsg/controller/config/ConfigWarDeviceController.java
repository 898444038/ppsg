package com.ming.ppsg.controller.config;

import com.ming.ppsg.service.ConfigCountryService;
import com.ming.system.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ppsg/config/warDevice")
public class ConfigWarDeviceController {

    @Autowired
    private ConfigCountryService configCountryService;

    @GetMapping("/list")
    public ResultMsg list(){
        return ResultMsg.success(configCountryService.select());
    }

}
