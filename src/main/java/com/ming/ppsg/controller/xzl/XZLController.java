package com.ming.ppsg.controller.xzl;

import com.ming.system.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@RequestMapping("/ppsg/xzl")
public class XZLController {

    @RequestMapping("/index")
    public String index(){
        return "ppsg/xzl/index";
    }

}
