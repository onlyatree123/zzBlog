package com.zz.blog.consoleUser.controller;

import com.zz.blog.base.controller.BaseController;
import com.zz.blog.user.service.UserService;
import com.zz.blog.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/console/user")
@Controller
public class CsUserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/update")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserVo vo) {
        //userService
        return new ResponseEntity<UserVo>(vo, HttpStatus.OK);
    }
}
