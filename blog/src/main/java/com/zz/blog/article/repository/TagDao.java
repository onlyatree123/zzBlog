package com.zz.blog.article.repository;

import com.zz.blog.article.entity.Article;
import com.zz.blog.article.entity.Tag;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagDao  extends PagingAndSortingRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

}
