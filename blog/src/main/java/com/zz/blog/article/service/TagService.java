package com.zz.blog.article.service;

import com.zz.blog.article.entity.Tag;
import com.zz.blog.article.repository.TagDao;
import com.zz.blog.base.service.BaseService;
import com.zz.blog.base.util.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService extends BaseService {
    @Autowired
    private TagDao tagDao;

    public void saveTag(Tag tag){
        if (tag.getId()!=null){
            Tag t=tagDao.findById(tag.getId()).get();
            BeanMapper.copy(t,tag);
            tagDao.save(tag);
        }else {
            tagDao.save(tag);
        }
    }
}
