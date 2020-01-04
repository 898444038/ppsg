package com.ming.ppsg.mapper;

import com.ming.ppsg.entity.DiscussPostTags;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscussPostTagsMapper {
    List<DiscussPostTags> selectAll();

    int insert(DiscussPostTags tags);
}
