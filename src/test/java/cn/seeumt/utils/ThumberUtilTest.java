package cn.seeumt.utils;

import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/4 20:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ThumberUtilTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void hi() {
        User beautiful = userMapper.selectById("Beautiful");
        System.out.println(beautiful);

    }
}
