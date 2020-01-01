package com.ming.ppsg.controller.generals;

import com.ming.system.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@RequestMapping("/ppsg/generals")
public class GeneralsController {

    @RequestMapping("/index")
    public String index(){
        return "ppsg/generals/index";
    }

}
