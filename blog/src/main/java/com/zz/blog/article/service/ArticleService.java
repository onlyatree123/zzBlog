package com.zz.blog.article.service;

import com.google.common.collect.Lists;
import com.zz.blog.article.entity.Article;
import com.zz.blog.article.repository.ArticleDao;
import com.zz.blog.article.vo.ArticleVo;
import com.zz.blog.base.service.BaseService;
import com.zz.blog.base.util.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService extends BaseService {

    @Autowired
    private ArticleDao articleDao;

    public List<ArticleVo> getAllArticles(){
        List<ArticleVo> articleVos= Lists.newArrayList();
        List<Article> articles=(List<Article>)articleDao.findAll();
        if (articles.size()>0){
            articles.forEach(a->{
                ArticleVo articleVo=BeanMapper.map(a,ArticleVo.class);
                articleVos.add(articleVo);
            });
        }
        return articleVos;
    }

    public void saveArticle(Article vo){
        if (vo.getId()!=null){
            Article article=articleDao.findById(vo.getId()).get();
            BeanMapper.copy(article,vo);
            vo.setUpdateDate(new Date());
            articleDao.save(vo);
        }else {
            articleDao.save(vo);
        }
    }

    public ArticleVo getArticleInfo(Long id){
        Article article=articleDao.findById(id).get();
        return BeanMapper.map(article,ArticleVo.class);
    }
}
