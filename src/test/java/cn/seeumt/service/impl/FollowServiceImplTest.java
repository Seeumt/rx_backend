package cn.seeumt.service.impl;

import cn.seeumt.service.FollowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/10 20:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FollowServiceImplTest {
    @Autowired
    private FollowService followService;
    @Test
    public void getAllLiker() {
        System.out.println(followService.getAllLiker("seeumt").size());
    }
}
