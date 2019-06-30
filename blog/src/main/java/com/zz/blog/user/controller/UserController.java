package com.zz.blog.user.controller;

import com.zz.blog.base.controller.BaseController;
import com.zz.blog.user.entity.User;
import com.zz.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping("/search/{id}")
    @ResponseBody
    public ResponseEntity<?> searchUser(@PathVariable("id")Long userId){
        User user=userService.getUserById(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
