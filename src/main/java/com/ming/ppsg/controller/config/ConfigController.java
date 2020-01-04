package com.ming.ppsg.controller.config;

import com.ming.system.annotation.Log;
import com.ming.system.annotation.methodtype.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@RequestMapping("/ppsg/config")
public class ConfigController {

    @Page
    @RequestMapping("/generals")
    public String generals(){
        return "ppsg/config/generals";
    }

    @Page
    @RequestMapping("/warDevice")
    public String warDevice(){
        return "ppsg/config/warDevice";
    }

}
