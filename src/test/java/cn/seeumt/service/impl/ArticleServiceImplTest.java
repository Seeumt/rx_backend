package cn.seeumt.service.impl;

import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.dataobject.Article;
import cn.seeumt.utils.UuidUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Seeumt
 * @date 2019/12/8 12:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleServiceImplTest {
    @Autowired
    private ArticleMapper articleMapper;
    @Test
    void insert() {
        Article article = new Article();
        String id = UuidUtil.getUUID();
        article.setId(id);
        article.setTitle("第一篇文章");
        article.setMdContent("第一篇文章的mdcontent");
        article.setHtmlContent("第一篇文章的htmlcontent");
        String loveId = UuidUtil.getUUID();
        article.setLoveId(loveId);
        String commentId = UuidUtil.getUUID();
        article.setCommentId(commentId);
        String userId = UuidUtil.getUUID();
        article.setUserId(userId);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setEnabled(true);
        article.setDeleted(false);
        articleMapper.insert(article);
        System.out.println(article);
    }
}
