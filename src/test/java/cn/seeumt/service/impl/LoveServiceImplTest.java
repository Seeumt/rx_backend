package cn.seeumt.service.impl;

import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.dao.LoveMapper;
import cn.seeumt.dataobject.Article;
import cn.seeumt.dataobject.Love;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Seeumt
 * @date 2019/12/8 12:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class LoveServiceImplTest {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private LoveMapper loveMapper;

    @Test
    void addLove() {
        String articleId = "1bd4d5a79bbb494f81f3c4dfffad718d";
        Article article = articleMapper.selectByPrimaryKey(articleId);
        Love love = new Love();
        love.setLoveId(article.getLoveId());
        love.setType((byte) 1);
        love.setStatus(true);
        love.setCreateTime(new Date());
        love.setUpdateTime(new Date());
        love.setEnabled(true);
        loveMapper.insert(love);
    }
}
