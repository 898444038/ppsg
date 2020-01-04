package com.ming.system.controller;

import com.ming.system.annotation.Log;
import com.ming.system.annotation.methodtype.Page;
import com.ming.system.entity.Permisson;
import com.ming.system.service.impl.MyUserDetailsService;
import com.ming.system.utils.ResultMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping

public class IndexController {
    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Log
    @Page
    @RequestMapping("/index")
    public String index(HttpSession session){
        List<Permisson> menuList = (List<Permisson>)session.getAttribute("menuList");
        if(null != menuList){
            for (Permisson permisson : menuList) {
                permisson.setIsOpen(0);
                permisson.setIsActive(0);
            }
        }
        return "index";
    }

    @RequestMapping("/getMenus")
    @ResponseBody
    public ResultMsg getMenus(HttpSession session){
        List<Permisson> menuList = (List<Permisson>)session.getAttribute("menuList");
        if(null == menuList){
            menuList = myUserDetailsService.getResource();
            session.setAttribute("menuList",menuList);
        }
//        List<Permisson> menuList = new ArrayList<>();
//        String icon = "zwicon-cog";
//        String icon2 = "";//"zwicon-slider-circle-h";
//        menuList.add(new Permisson(10001L,"卡册",icon,0L,""));
//        menuList.add(new Permisson(10002L,"武将图鉴",icon2,10001L,"/ppsg/generals/index"));
//        menuList.add(new Permisson(10003L,"最佳随从",icon2,10001L,""));
//
//        menuList.add(new Permisson(20001L,"配置",icon,0L,""));
//        menuList.add(new Permisson(20002L,"武将配置",icon2,20001L,"/ppsg/config/generals"));
//        menuList.add(new Permisson(20003L,"战器配置",icon2,20001L,"/ppsg/config/warDevice"));
//        menuList.add(new Permisson(20004L,"兵符配置",icon2,20001L,""));
//        menuList.add(new Permisson(20005L,"兵书配置",icon2,20001L,""));
//        menuList.add(new Permisson(20006L,"战意配置",icon2,20001L,""));
//        menuList.add(new Permisson(20007L,"命格配置",icon2,20001L,""));
//        menuList.add(new Permisson(20008L,"工坊配置",icon2,20001L,""));
//        menuList.add(new Permisson(20009L,"战阵配置",icon2,20001L,""));
//
//        menuList.add(new Permisson(30001L,"战力",icon,0L,""));
//        menuList.add(new Permisson(30002L,"战力计算器",icon2,30001L,""));
//        menuList.add(new Permisson(30003L,"虚战力查询",icon2,30001L,"/ppsg/xzl/index"));
//
//        menuList.add(new Permisson(40001L,"活动",icon,0L,""));
//        menuList.add(new Permisson(40002L,"活动图",icon2,40001L,""));
//        menuList.add(new Permisson(40003L,"充值消费计算",icon2,40001L,""));
        return ResultMsg.success(menuList);
    }

    @RequestMapping("/setMenuStatus")
    @ResponseBody
    public ResultMsg setMenuStatus(Long id,Integer isOpen,HttpSession session){
        List<Permisson> menuList = (List<Permisson>)session.getAttribute("menuList");
        if(null == menuList){
            return ResultMsg.failed();
        }
        for (Permisson permisson : menuList) {
            if(permisson.getId().equals(id)){
                permisson.setIsOpen(isOpen);
            }
        }
        return ResultMsg.success();
    }

    @RequestMapping("/setMenuActive")
    @ResponseBody
    public ResultMsg setMenuActive(Long id,HttpSession session){
        List<Permisson> menuList = (List<Permisson>)session.getAttribute("menuList");
        if(null == menuList){
            return ResultMsg.failed();
        }
        for (Permisson permisson : menuList) {
            if(permisson.getId().equals(id)){
                permisson.setIsActive(1);
            }else{
                permisson.setIsActive(0);
            }
        }
        return ResultMsg.success();
    }
}
