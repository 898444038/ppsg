package com.ming.ppsg.controller.strategy;

import com.ming.ppsg.entity.DiscussPost;
import com.ming.ppsg.service.DiscussPostService;
import com.ming.system.annotation.Log;
import com.ming.system.annotation.methodtype.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/1/3 0003.
 */
@Log
@Controller
@RequestMapping("/ppsg/strategy")
public class StrategyController {

    @Resource
    private DiscussPostService discussPostService;

    @Page
    @RequestMapping("/discuss")
    public String mass(){
        return "ppsg/strategy/discuss";
    }

    @Page
    @RequestMapping("/discuss/detail/{id}")
    public String massDetail(@PathVariable("id")Long id, HttpServletRequest request){
        DiscussPost discussPost = discussPostService.detail(id);
        if(StringUtils.isNotBlank(discussPost.getImages())){
            String[] arr = discussPost.getImages().split(",");
            List<String> list = new ArrayList<>();
            for (String img :arr){
                list.add(img);
            }
            discussPost.setImageUrlList(list);
        }
        request.setAttribute("discussPost",discussPost);
        return "ppsg/strategy/discuss_detail";
    }

    @Page
    @RequestMapping("/discuss/add")
    public String add(){
        return "ppsg/strategy/discuss_add";
    }
}
