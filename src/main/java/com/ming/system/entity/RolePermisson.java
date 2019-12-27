package com.ming.system.entity;

/**
 * Created by Administrator on 2019/12/26 0026.
 */
public class RolePermisson {

    private Long roleId;
    private Long permissonId;
    private String url;
    private String roleName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissonId() {
        return permissonId;
    }

    public void setPermissonId(Long permissonId) {
        this.permissonId = permissonId;
    }
}