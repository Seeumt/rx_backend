package cn.seeumt.service.impl;

import cn.seeumt.dao.CarouselMapper;
import cn.seeumt.dataobject.Carousel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CarouselServiceImplTest {

    @Autowired
    private CarouselMapper carouselMapper;
    @Test
    public void getCarousels() {
        QueryWrapper<Carousel> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0);
        System.out.println(carouselMapper.selectList(wrapper));
    }
}
