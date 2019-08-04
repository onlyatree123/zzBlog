package com.zz.blog.consoleUser.repository;

import com.zz.blog.consoleUser.entity.UserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRoleDao extends PagingAndSortingRepository<UserRole,Long>,JpaSpecificationExecutor<UserRole> {
//    @Query(value = "SELECT FROM user_role ur join left role r on ur.role_id=r.id" +
//            " ",nativeQuery = true)
//    List<String> getRolePermissionsByUserId();
}
