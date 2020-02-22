package cn.seeumt.service.impl;

import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.User;
import cn.seeumt.enums.Tips;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
        Long nowTime = System.currentTimeMillis();
        Date nowDate = new Date(nowTime);
        Date validDate = new Date(nowTime-100000);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("last_visit_time", validDate, nowDate);
        Integer integer = userMapper.selectCount(queryWrapper);
    }

    @Test
    public void selectByUserId() {
        System.out.println(userMapper.selectById("aamm"));
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUserId("aammb");
        user.setUsername("bbssb");
        user.setNickname("dd");
        user.setPassword("ss");
        user.setTelephone(Tips.DEFAULT_TEL.getMsg());
        user.setFaceIcon("ss");
        user.setEnabled(false);
        user.setLocked(false);
        user.setCreateTime(new Date());
        user.setLastVisitTime(new Date());
        user.setIsRememberMe(false);
        user.setReseted(false);
        user.setOpenId("xxx");
        System.out.println(userMapper.insert(user));
    }

    @Test
    public void bindTel() {
    }

    @Test
    public void online() {
        Long nowTime = System.currentTimeMillis();
        Date nowDate = new Date(nowTime);
        Date validDate = new Date(nowTime-300000L);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("last_visit_time", validDate, nowDate);
        System.out.println(userMapper.selectCount(queryWrapper));
    }
}
