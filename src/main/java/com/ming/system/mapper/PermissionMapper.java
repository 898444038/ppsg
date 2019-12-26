package com.ming.system.mapper;

/**
 * Created by Administrator on 2019/12/26 0026.
 */

import com.ming.system.entity.RolePermisson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface PermissionMapper {

    @Select( "SELECT A.role_name AS roleName,C.url FROM s_role AS A LEFT JOIN s_role_permission B ON A.id=B.role_id LEFT JOIN s_permission AS C ON B.permission_id=C.id" )
    List<RolePermisson> getRolePermissions();

}