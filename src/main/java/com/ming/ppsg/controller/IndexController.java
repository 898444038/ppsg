package com.ming.ppsg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
