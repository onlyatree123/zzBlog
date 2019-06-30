package com.zz.blog.user.service;

import com.zz.blog.base.service.BaseService;
import com.zz.blog.user.entity.User;
import com.zz.blog.user.repostory.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends BaseService{
    @Autowired
    private UserDao userDao;

    public User getUserById(Long userId){
        return  userDao.findById(userId).get();
    }
}
