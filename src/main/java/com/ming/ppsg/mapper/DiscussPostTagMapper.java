package com.ming.ppsg.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DiscussPostTagMapper {
    @Insert({ "insert into ppsg_discuss_post_tag(discuss_id, tag_id) values(#{discussId}, #{tagId})" })
    int insertPostTag(@Param("discussId") Long discussId, @Param("tagId") Long tagId);
}
