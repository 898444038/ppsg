package com.ming.ppsg.service.impl;

import com.ming.ppsg.entity.DiscussPost;
import com.ming.ppsg.entity.DiscussPostTags;
import com.ming.ppsg.mapper.DiscussPostMapper;
import com.ming.ppsg.mapper.DiscussPostTagMapper;
import com.ming.ppsg.service.DiscussPostService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DiscussPostServiceImpl implements DiscussPostService {

    @Resource
    private DiscussPostMapper discussPostMapper;
    @Resource
    private DiscussPostTagMapper discussPostTagMapper;

    @Override
    public List<DiscussPost> selectAll(DiscussPost post) {
        return discussPostMapper.selectAll(post);
    }

    @Override
    public int insert(DiscussPost post,String tagIds) {
        int i = discussPostMapper.insert(post);
        String[] ids = tagIds.split(",");
        for(String id : ids){
            discussPostTagMapper.insertPostTag(post.getId(),Long.valueOf(id));
        }
        return i;
    }

    @Override
    public Map<String, Object> selectCount(Long userId) {
        return discussPostMapper.selectCount(userId);
    }

    @Override
    public DiscussPost detail(Long id) {
        return discussPostMapper.detail(id);
    }
}
