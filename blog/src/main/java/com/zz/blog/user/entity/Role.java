package com.zz.blog.user.entity;

import com.zz.blog.base.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "role")
@Entity
public class Role extends IdEntity{
    private String roleName;
    private String roleDesc;
    private String permissions;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
