package cn.seeumt.controller;

import cn.seeumt.service.MediaTagsService;
import cn.seeumt.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/16 13:22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MediaTagsControllerTest {
    @Autowired
    private MediaTagsService mediaTagsService;
    @Test
    public void getPicture() {
        List<String> ids = mediaTagsService.findTagIdsByParentId("c79f2c08ab0447e2a2b5ea416ede6e62");
        System.out.println(ids);
    }
}
