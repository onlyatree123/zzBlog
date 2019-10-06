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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/open/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserVo vo) {
        userService.registerUser(vo);
        return new ResponseEntity<>(GetSuccMap(), HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> userLogin(@RequestBody UserVo vo) {
        Map<String,String> map=userService.userLogin(vo);
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
    }
}
