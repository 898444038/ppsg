package com.ming.system.controller;

import com.ming.system.entity.Menu;
import com.ming.system.utils.ResultMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        //用户验证
        //菜单

        return "index";
    }

    @RequestMapping("/getMenus")
    @ResponseBody
    public ResultMsg getMenus(){
        List<Menu> menuList = new ArrayList<>();
        String icon = "zwicon-cog";
        String icon2 = "";//"zwicon-slider-circle-h";

        menuList.add(new Menu(10001L,"武将配置",icon,0L,""));
        menuList.add(new Menu(10002L,"卡册",icon2,10001L,"/ppsg/generals"));
        menuList.add(new Menu(10003L,"配置",icon2,10001L,"/ppsg/config/list"));

        menuList.add(new Menu(20001L,"战力",icon,0L,""));
        menuList.add(new Menu(20002L,"战力计算器",icon2,20001L,"/ppsg/generals"));
        menuList.add(new Menu(20003L,"虚战力榜",icon2,20001L,"/ppsg/generals"));

        menuList.add(new Menu(30001L,"活动",icon,0L,""));
        menuList.add(new Menu(30002L,"活动图",icon2,30001L,"/ppsg/generals"));
        menuList.add(new Menu(30003L,"充值消费计算",icon2,30001L,"/ppsg/generals"));
        return ResultMsg.success(menuList);
    }
}
