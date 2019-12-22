package com.ming.ppsg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ppsg/config")
public class ConfigController {

    @RequestMapping("/list")
    public String list(){
        return "ppsg/config/list";
    }

}
