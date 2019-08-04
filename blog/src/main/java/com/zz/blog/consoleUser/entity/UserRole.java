package com.zz.blog.consoleUser.entity;

import com.zz.blog.base.entity.IdEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user_role")
@Entity
public class UserRole extends IdEntity {
    private Long userId;
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
