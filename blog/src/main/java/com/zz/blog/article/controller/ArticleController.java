package com.zz.blog.article.controller;

import com.zz.blog.article.entity.Article;
import com.zz.blog.article.service.ArticleService;
import com.zz.blog.article.vo.ArticleVo;
import com.zz.blog.base.constants.BusinessStatus;
import com.zz.blog.base.controller.BaseController;
import org.apache.commons.collections.BagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    /**
     * 文章列表
     * @param vo
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> getAllArticles(ArticleVo vo) {
        List<ArticleVo> articleVos = articleService.getAllArticles();
        return new ResponseEntity<List<ArticleVo>>(articleVos, HttpStatus.OK);
    }

    /**
     * 文章保存/更新
     * @param vo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveArticle(Article article) {
        articleService.saveArticle(article);
        return new ResponseEntity<>(GetSuccMap(),HttpStatus.OK);
    }

    /**
     * 查看文章详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getArticle(@PathVariable Long id) {
        if (id==null){
            throwException(BusinessStatus.ERROR,"id is null");
        }
        ArticleVo articleVo=articleService.getArticleInfo(id);
        return new ResponseEntity<>(articleVo,HttpStatus.OK);
    }

}
