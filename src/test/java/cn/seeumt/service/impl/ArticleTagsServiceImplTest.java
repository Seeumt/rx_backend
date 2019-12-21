package cn.seeumt.service.impl;


import cn.seeumt.dao.ArticleTagsMapper;
import cn.seeumt.service.ArticleTagsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/21 21:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleTagsServiceImplTest {
    @Autowired

    private ArticleTagsService articleTagsService;
    @Test
    public void findTagIdsByArticleId() {

        List<String> tagIdsByArticleId = articleTagsService.findTagIdsByArticleId("123456789");
        System.out.println(tagIdsByArticleId);
    }
}
