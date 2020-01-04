package com.ming.ppsg.controller.generals;

import com.ming.system.annotation.Log;
import com.ming.system.annotation.methodtype.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@RequestMapping("/ppsg/generals")
public class GeneralsController {

    @Page
    @RequestMapping("/index")
    public String index(){
        return "ppsg/generals/index";
    }

    @Page
    @RequestMapping("/add")
    public String add(){
        return "ppsg/generals/generals_add";
    }
}
