package cn.seeumt.service.impl;

import cn.seeumt.dataobject.Tag;
import cn.seeumt.service.TagService;

import cn.seeumt.vo.TagVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @date 2019/12/21 21:29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TagServiceImplTest {
    @Autowired
    private TagService tagService;
    @Test
    public void findByTagIds() {
        String[] tagIdsArr = {"1", "2", "3", "5"};
        List<String> tagIdsList = Arrays.asList(tagIdsArr);
        List<TagVO> tagVOS = tagService.findTagVOByTagIds(tagIdsList);
        System.out.println(tagVOS);
    }
}
