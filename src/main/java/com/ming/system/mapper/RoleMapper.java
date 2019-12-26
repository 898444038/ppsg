package com.ming.system.mapper;

import com.ming.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2019/12/26 0026.
 */
@Mapper
public interface RoleMapper {

    @Select("SELECT A.id,A.role_name FROM s_role A LEFT JOIN s_user_role B ON A.id=B.role_id WHERE B.user_id=${userId}")
    List<Role> getRolesByUserId(@Param("userId") Long userId);

}
