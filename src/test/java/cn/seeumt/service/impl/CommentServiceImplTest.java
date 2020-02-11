package cn.seeumt.service.impl;

import cn.seeumt.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/10 18:29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void createComment() {
    }

    @Test
    public void findNextLevelCommentsByParentId() {
    }

    @Test
    public void selectCommentCountByRootIdAndType() {
        System.out.println(commentService.selectCommentCountByRootIdAndType("227b8b1afb43408897d1dbb81eaa2ab9", (byte) 3).size());
    }

    @Test
    public void commentForRoot() {
    }

    @Test
    public void comment() {
    }

    @Test
    public void selectByCommentId() {
    }

    @Test
    public void findUserCommentsOfAnArticle() {
    }

    @Test
    public void findUserCommentsOfAnComments() {
    }
}
