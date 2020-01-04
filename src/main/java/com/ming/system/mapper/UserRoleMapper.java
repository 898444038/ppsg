package com.ming.system.mapper;

import com.ming.system.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2019/12/26 0026.
 */
@Mapper
public interface UserRoleMapper {

    @Insert({ "insert into s_user_role(user_id, role_id) values(#{userId}, #{roleId})" })
    int insertUserRole(@Param("userId") Long userId,@Param("roleId") Long roleId);

}