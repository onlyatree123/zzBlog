package com.zz.blog.article.controller;

import com.zz.blog.article.entity.Article;
import com.zz.blog.article.entity.Tag;
import com.zz.blog.article.service.TagService;
import com.zz.blog.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TagController extends BaseController {
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveTag(Tag tag) {
        tagService.saveTag(tag);
        return new ResponseEntity<>(GetSuccMap(), HttpStatus.OK);
    }

}
