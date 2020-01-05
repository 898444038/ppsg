package com.ming.ppsg.mapper;

import com.ming.ppsg.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectAll(DiscussPost post);

    int insert(DiscussPost post);

    Map<String, Object> selectCount(Long userId);

    DiscussPost detail(Long id);
}
