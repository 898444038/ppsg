package com.ming.system.controller;

import com.ming.system.annotation.Log;
import com.ming.system.entity.Permisson;
import com.ming.system.utils.ResultMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@Log
public class IndexController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        return "index";
    }

    @RequestMapping("/getMenus")
    @ResponseBody
    public ResultMsg getMenus(){
        List<Permisson> menuList = new ArrayList<>();
        String icon = "zwicon-cog";
        String icon2 = "";//"zwicon-slider-circle-h";
        menuList.add(new Permisson(10001L,"卡册",icon,0L,""));
        menuList.add(new Permisson(10002L,"武将图鉴",icon2,10001L,"/ppsg/generals/index"));
        menuList.add(new Permisson(10003L,"最佳随从",icon2,10001L,""));

        menuList.add(new Permisson(20001L,"配置",icon,0L,""));
        menuList.add(new Permisson(20002L,"武将配置",icon2,20001L,"/ppsg/config/generals"));
        menuList.add(new Permisson(20003L,"战器配置",icon2,20001L,"/ppsg/config/warDevice"));
        menuList.add(new Permisson(20004L,"兵符配置",icon2,20001L,""));
        menuList.add(new Permisson(20005L,"兵书配置",icon2,20001L,""));
        menuList.add(new Permisson(20006L,"战意配置",icon2,20001L,""));
        menuList.add(new Permisson(20007L,"命格配置",icon2,20001L,""));
        menuList.add(new Permisson(20008L,"工坊配置",icon2,20001L,""));
        menuList.add(new Permisson(20009L,"战阵配置",icon2,20001L,""));

        menuList.add(new Permisson(30001L,"战力",icon,0L,""));
        menuList.add(new Permisson(30002L,"战力计算器",icon2,30001L,""));
        menuList.add(new Permisson(30003L,"虚战力查询",icon2,30001L,"/ppsg/xzl/index"));

        menuList.add(new Permisson(40001L,"活动",icon,0L,""));
        menuList.add(new Permisson(40002L,"活动图",icon2,40001L,""));
        menuList.add(new Permisson(40003L,"充值消费计算",icon2,40001L,""));
        return ResultMsg.success(menuList);
    }
}
