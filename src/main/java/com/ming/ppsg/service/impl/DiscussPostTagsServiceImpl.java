package com.ming.ppsg.service.impl;

import com.ming.ppsg.entity.DiscussPostTags;
import com.ming.ppsg.mapper.DiscussPostTagsMapper;
import com.ming.ppsg.service.DiscussPostTagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiscussPostTagsServiceImpl implements DiscussPostTagsService {

    @Resource
    private DiscussPostTagsMapper disCussPostTagsMapper;

    @Override
    public List<DiscussPostTags> selectAll() {
        return disCussPostTagsMapper.selectAll();
    }

    @Override
    public int insert(DiscussPostTags tags) {
        return disCussPostTagsMapper.insert(tags);
    }
}
