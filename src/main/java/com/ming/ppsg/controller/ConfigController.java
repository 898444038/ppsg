package com.ming.ppsg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ppsg/config")
public class ConfigController {

    /**
     * 武将配置页
     */
    @RequestMapping("/generals")
    public String generals(){
        return "ppsg/config/generals";
    }

    /**
     * 战器配置页
     */
    @RequestMapping("/warDevice")
    public String warDevice(){
        return "ppsg/config/warDevice";
    }

    /**
     * 兵符配置页
     */
    @RequestMapping("/symbols")
    public String symbols(){
        return "ppsg/config/symbols";
    }
}
