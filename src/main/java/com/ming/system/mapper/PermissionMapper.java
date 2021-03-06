package com.ming.system.mapper;

import com.ming.system.entity.Permisson;
import com.ming.system.entity.RolePermisson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select( "SELECT A.NAME AS roleName,C.url FROM s_role AS A LEFT JOIN s_role_permission B ON A.id=B.role_id LEFT JOIN s_permission AS C ON B.permission_id=C.id where C.type=1" )
    List<RolePermisson> getRolePermissions();

    @Select("select id,url,name,description,pid,icon,is_open as isOpen,type from s_permission where type=0")
    List<Permisson> getResource();
}