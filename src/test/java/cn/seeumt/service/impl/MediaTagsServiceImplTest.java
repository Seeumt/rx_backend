package cn.seeumt.service.impl;


import cn.seeumt.service.MediaTagsService;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/21 21:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MediaTagsServiceImplTest {
    @Autowired

    private MediaTagsService mediaTagsService;
    @Test
    public void findTagIdsByParentId() {

        List<String> tagIdsByParentId = mediaTagsService.findTagIdsByParentId("227b8b1afb43408897d1dbb81eaa2ab9");
        System.out.println(tagIdsByParentId);
    }
    @Test
    public void TestString(){
            List<String> list = new ArrayList<>();
            list.add("ROLE_ADMIN");
            list.add("ROLE_USER");
            System.out.println(list);
            System.out.println(list.toString());

            String json = JSONArray.toJSONString(list);//把list转换成json格式的String类型
            System.out.println("json格式的String类型 " + json);

            System.out.println(StringUtils.strip(list.toString(),"[]"));
            String str = StringUtils.strip(list.toString(), "[]");

// 1.使用JDK,逗号分隔的字符串-->数组-->list
        List<String> result = Arrays.asList(str.split(","));
        System.out.println(result);
// 2.使用Apache Commons的StringUtils
        List<String> result1 = Arrays.asList(StringUtils.split(str, ","));
        System.out.println(result1);
// 3.通过遍历
        String[] strings = str.split(",");
        List<String> result2 = new ArrayList<String>();
        for (String string : strings) {
            result2.add(string);
        }
        System.out.println(result2);
        }


}
