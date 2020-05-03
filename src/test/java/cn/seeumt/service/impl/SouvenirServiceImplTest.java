package cn.seeumt.service.impl;

import cn.seeumt.dao.CategoryMapper;
import cn.seeumt.dataobject.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/4/24 17:03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SouvenirServiceImplTest {
    @Autowired
    private CategoryMapper categoryMapper;
    @Test
    public void getProductByKeywordCategory() {
    }

    @Test
    public void saveOrUpdateProduct() {
    }

    @Test
    public void listSimpleVO() {
    }

    @Test
    public void listFcSouvenirList() {
        List<Category> categories = categoryMapper.selectList(null);
        System.out.println(categories);
    }
}
