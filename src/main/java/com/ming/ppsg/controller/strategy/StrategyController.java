package com.ming.ppsg.controller.strategy;

import com.ming.system.annotation.Log;
import com.ming.system.annotation.methodtype.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2020/1/3 0003.
 */
@Log
@Controller
@RequestMapping("/ppsg/strategy")
public class StrategyController {

    @Page
    @RequestMapping("/discuss")
    public String mass(){
        return "ppsg/strategy/discuss";
    }

    @Page
    @RequestMapping("/discuss/detail")
    public String massDetail(){
        return "ppsg/strategy/discuss_detail";
    }
}
