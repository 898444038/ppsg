package com.ming.ppsg.service;

import com.ming.ppsg.entity.DiscussPostTags;

import java.util.List;

public interface DiscussPostTagsService {
    List<DiscussPostTags> selectAll();

    int insert(DiscussPostTags tags);
}
