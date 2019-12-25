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
        return "index2";
    }

    @RequestMapping("/head")
    public String head(){
        return "head";
    }

    @RequestMapping("/foot")
    public String foot(){
        return "foot";
    }

    @RequestMapping("/getMenus")
    @ResponseBody
    public ResultMsg getMenus(){
        List<Menu> menuList = new ArrayList<>();
        String icon = "zwicon-cog";
        String icon2 = "";//"zwicon-slider-circle-h";
        menuList.add(new Menu(10001L,"卡册",icon,0L,""));
        menuList.add(new Menu(10002L,"武将图鉴",icon2,10001L,"/ppsg/generals"));
        menuList.add(new Menu(10003L,"最佳随从",icon2,10001L,"/ppsg/generals"));

        menuList.add(new Menu(20001L,"配置",icon,0L,""));
        menuList.add(new Menu(20002L,"武将配置",icon2,20001L,"/ppsg/config/generals"));
        menuList.add(new Menu(20003L,"战器配置",icon2,20001L,"/ppsg/config/warDevice"));
        menuList.add(new Menu(20004L,"兵符配置",icon2,20001L,"/ppsg/config/symbols"));
        menuList.add(new Menu(20005L,"兵书配置",icon2,20001L,"/ppsg/config/list"));
        menuList.add(new Menu(20006L,"战意配置",icon2,20001L,"/ppsg/config/list"));
        menuList.add(new Menu(20007L,"命格配置",icon2,20001L,"/ppsg/config/list"));
        menuList.add(new Menu(20008L,"工坊配置",icon2,20001L,"/ppsg/config/list"));
        menuList.add(new Menu(20009L,"战阵配置",icon2,20001L,"/ppsg/config/list"));

        menuList.add(new Menu(30001L,"战力",icon,0L,""));
        menuList.add(new Menu(30002L,"战力计算器",icon2,30001L,"/ppsg/generals"));
        menuList.add(new Menu(30003L,"虚战力榜",icon2,30001L,"/ppsg/generals"));

        menuList.add(new Menu(40001L,"活动",icon,0L,""));
        menuList.add(new Menu(40002L,"活动图",icon2,40001L,"/ppsg/generals"));
        menuList.add(new Menu(40003L,"充值消费计算",icon2,40001L,"/ppsg/generals"));
        return ResultMsg.success(menuList);
    }
}
