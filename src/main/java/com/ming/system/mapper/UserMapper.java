package com.ming.system.mapper;

import com.ming.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2019/12/26 0026.
 */
@Mapper
public interface UserMapper {

    @Select( "select id,user_name,password from s_user where user_name = #{username}" )
    User loadUserByUsername(@Param("username") String username);

}