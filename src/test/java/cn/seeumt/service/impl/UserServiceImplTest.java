package cn.seeumt.service.impl;

import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/15 13:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void selectUserDetailByUserId() {
    }

    @Test
    public void selectUserDetailByUsername() {
    }

    @Test
    public void selectUserDetailByTelephone() {
    }

    @Test
    public void selectUserDetailByOpenId() {
    }

    @Test
    public void resetPwd() {
    }

    @Test
    public void createUserDetail() {
    }

    @Test
    public void uploadFaceIcon() {
    }

    @Test
    public void selectByTelephone() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", "53");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void bindTel() {
    }
}
