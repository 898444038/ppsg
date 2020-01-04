package com.ming.ppsg.controller.strategy;


import com.ming.ppsg.entity.DiscussPost;
import com.ming.ppsg.entity.DiscussPostTags;
import com.ming.ppsg.service.DiscussPostService;
import com.ming.ppsg.service.DiscussPostTagsService;
import com.ming.system.annotation.Log;
import com.ming.system.annotation.methodtype.Insert;
import com.ming.system.annotation.methodtype.Select;
import com.ming.system.entity.User;
import com.ming.system.utils.ResultMsg;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Log
@RestController
@RequestMapping("/ppsg/strategy/discuss/post")
public class DiscussPostController {

    @Resource
    private DiscussPostService disCussPostService;

    @Select
    @GetMapping("/list")
    public ResultMsg list(){
        return ResultMsg.success(disCussPostService.selectAll());
    }

    @Insert
    @PostMapping("/addPost")
    public ResultMsg addPost(DiscussPost post,String tagIds){
        if(StringUtils.isBlank(post.getTitle()) || StringUtils.isBlank(post.getContent()) || StringUtils.isBlank(tagIds)){
            return ResultMsg.failed();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        post.setUserId(user.getId());
        post.setCreateTime(new Date());
        post.setFabulousCount(0);
        post.setAnswerCount(0);
        post.setLookCount(0);
        post.setStatus(0);
        post.setDelFlag(0);
        int i = disCussPostService.insert(post,tagIds);
        if(i>0){
            return ResultMsg.success();
        }
        return ResultMsg.failed();
    }
}
