package cn.seeumt.service.impl;

import cn.seeumt.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/10 20:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceImplTest {

    @Autowired
    private PostService postService;
    @Test
    public void list() {
    }

    @Test
    public void selectByPostId() {
    }

    @Test
    public void sendPost() {
    }

    @Test
    public void selectByUserId() {
    }
}
