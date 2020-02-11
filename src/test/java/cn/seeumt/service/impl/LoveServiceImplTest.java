package cn.seeumt.service.impl;

import cn.seeumt.dao.LoveMapper;
import cn.seeumt.service.LoveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/10 21:27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoveServiceImplTest {

    @Autowired
    private LoveService loveService;

    @Test
    public void addOrCancelLove() {
    }

    @Test
    public void selectByApiRootId() {
    }

    @Test
    public void isLoveExist() {
    }

    @Test
    public void selectByApiRootIdAndUserId() {
    }

    @Test
    public void selectThumbCountByRootIdAndType() {
        System.out.println(loveService.selectThumbCountByRootIdAndType("227b8b1afb43408897d1dbb81eaa2ab9", (byte) 3).size());
    }
}
