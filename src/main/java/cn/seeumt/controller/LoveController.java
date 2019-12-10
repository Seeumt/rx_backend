package cn.seeumt.controller;

import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.dao.LoveMapper;
import cn.seeumt.dataobject.Article;
import cn.seeumt.dataobject.Love;
import cn.seeumt.enums.Tips;
import cn.seeumt.service.LoveService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author Seeumt
 * @date 2019/12/8 13:12
 */
@Controller
@RequestMapping("/love")
public class LoveController {

    @Autowired
    private LoveService loveService;

    @PostMapping(value = "/post")
    @ResponseBody
    public int addLove(@RequestParam("postId") String apiRootId,
                        @RequestParam("userId") String userId) {
        int i = loveService.addLove(apiRootId, userId);
        return i;
    }
}
