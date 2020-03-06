package cn.seeumt.service.impl;

import cn.seeumt.service.ArticleService;
import cn.seeumt.vo.ResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/3/6 19:28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;
    @Test
    public void query() {
    }

    @Test
    public void queryAll() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectById() {
    }

    @Test
    public void search() {
        System.out.println(articleService.search("", 1, 5));

    }

    @Test
    public void assembleArticleVO() {
    }
}
