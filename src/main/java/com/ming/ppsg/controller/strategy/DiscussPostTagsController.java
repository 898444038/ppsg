package com.ming.ppsg.controller.strategy;


import com.ming.ppsg.entity.DiscussPostTags;
import com.ming.ppsg.service.DiscussPostTagsService;
import com.ming.system.annotation.Log;
import com.ming.system.annotation.methodtype.Insert;
import com.ming.system.annotation.methodtype.Select;
import com.ming.system.utils.ResultMsg;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Log
@RestController
@RequestMapping("/ppsg/strategy/discuss/post/tags")
public class DiscussPostTagsController {

    @Resource
    private DiscussPostTagsService disCussPostTagsService;

    @Select
    @GetMapping("/list")
    public ResultMsg list(){
        return ResultMsg.success(disCussPostTagsService.selectAll());
    }

    @Insert
    @PostMapping("/addTags")
    public ResultMsg addTags(DiscussPostTags tags){
        if(StringUtils.isBlank(tags.getName())){
            return ResultMsg.failed();
        }
        tags.setCreateTime(new Date());
        tags.setDelFlag(0);
        int i = disCussPostTagsService.insert(tags);
        if(i>0){
            return ResultMsg.success();
        }
        return ResultMsg.failed();
    }
}
