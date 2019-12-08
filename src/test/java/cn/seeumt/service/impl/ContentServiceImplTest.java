package cn.seeumt.service.impl;

import cn.seeumt.dao.ContentMapper;
import cn.seeumt.dataobject.Content;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seeumt
 * @date 2019/12/8 19:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class ContentServiceImplTest {

    @Autowired
    private ContentMapper contentMapper;

    @Test
    void selectByContentId() {
        Content content = contentMapper.selectByContentId("e0730ec6551b4fa699e815faff8476b6");
        System.out.println(content);
    }

}
