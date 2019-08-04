package com.zz.blog.user.repository;

import com.zz.blog.user.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User,Long>,JpaSpecificationExecutor<User>{
    User findUserByLoginName(String loginName);
}
