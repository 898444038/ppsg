package com.ming.ppsg.service;

import com.ming.ppsg.entity.DiscussPost;

import java.util.List;

public interface DiscussPostService {
    List<DiscussPost> selectAll();

    int insert(DiscussPost post,String tagIds);
}
