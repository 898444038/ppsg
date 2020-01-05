package com.ming.ppsg.service;

import com.ming.ppsg.entity.DiscussPost;

import java.util.List;
import java.util.Map;

public interface DiscussPostService {
    List<DiscussPost> selectAll(DiscussPost post);

    int insert(DiscussPost post,String tagIds);

    Map<String,Object> selectCount(Long userId);

    DiscussPost detail(Long id);
}
