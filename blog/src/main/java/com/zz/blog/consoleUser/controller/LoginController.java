package com.zz.blog.consoleUser.controller;

import com.zz.blog.base.constants.BusinessStatus;
import com.zz.blog.base.controller.BaseController;
import com.zz.blog.user.service.UserService;
import com.zz.blog.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserVo vo){
        if (vo.getLoginName()==null){
           throwException(BusinessStatus.ERROR,"loginName is null");
        }
        if (vo.getLoginPassword()==null){
            throwException(BusinessStatus.ERROR,"password is null");
        }
        Map<String,String> map=userService.userLogin(vo);
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
    }
}
