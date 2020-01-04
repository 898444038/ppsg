package com.ming.ppsg.mapper;

import com.ming.ppsg.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectAll();

    int insert(DiscussPost post);
}
