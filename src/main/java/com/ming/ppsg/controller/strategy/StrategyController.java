package com.ming.ppsg.controller.strategy;

import com.ming.system.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2020/1/3 0003.
 */
@Log
@Controller
@RequestMapping("/ppsg/strategy")
public class StrategyController {

    @RequestMapping("/discuss")
    public String mass(){
        return "ppsg/strategy/discuss";
    }

    @RequestMapping("/discuss/detail")
    public String massDetail(){
        return "ppsg/strategy/discuss_detail";
    }
}
