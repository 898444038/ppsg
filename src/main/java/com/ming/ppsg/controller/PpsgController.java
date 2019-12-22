package com.ming.ppsg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ppsg")
public class PpsgController {

    @RequestMapping("/generals")
    public String generals(){
        return "ppsg/generals";
    }

    @RequestMapping("/generalsAdd")
    public String generalsAdd(){
        return "ppsg/generals_add";
    }

}
