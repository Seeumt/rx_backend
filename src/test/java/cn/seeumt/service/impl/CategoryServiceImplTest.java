package cn.seeumt.service.impl;

import cn.seeumt.dao.CategoryMapper;
import cn.seeumt.dataobject.Category;
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
 * @date 2020/1/30 17:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void selectCategoryTreeByCategoryId() {
    }

    @Test
    public void create() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void put() {
    }

    @Test
    public void getCategoryTree() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        System.out.println(categoryMapper.selectList(wrapper));
    }
}
