package com.ming.system.mapper;

import com.ming.system.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by Administrator on 2019/12/26 0026.
 */
@Mapper
public interface UserMapper {

    @Select( "select id,username,password,head_portrait as headPortrait,email,skin,disabled,del_flag as delFlag from s_user where username = #{username} and del_flag=0" )
    User loadUserByUsername(@Param("username") String username);

    @Select( "select count(1) from s_user where username = #{username}" )
    int checkUsername(@Param("username") String username);

    @Insert({ "insert into s_user(username,password,head_portrait,email,phone,skin,create_time,disabled,del_flag) values(#{username},#{password},#{headPortrait},#{email},#{phone},#{skin},#{createTime},#{disabled},#{delFlag})" })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int register(User user);
}