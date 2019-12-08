package cn.seeumt.controller;

import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.dao.LoveFromUserMapper;
import cn.seeumt.dao.LoveMapper;
import cn.seeumt.dataobject.Article;
import cn.seeumt.dataobject.Love;
import cn.seeumt.dataobject.LoveFromUser;
import cn.seeumt.enums.Tips;
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
    private ArticleMapper articleMapper;
    @Autowired
    private LoveMapper loveMapper;
    @Autowired
    private LoveFromUserMapper loveFromUserMapper;

    @PostMapping(value = "/")
    @ResponseBody
    public void addLove(@RequestParam("articleId") String articleId,
                        @RequestParam("userId") String userId) {

        Article article = articleMapper.selectByPrimaryKey(articleId);
        Love love = loveMapper.selectByLoveIdAndType(article.getLoveId(), Tips.ARTICLE_THUMB.getCode());
        LoveFromUser loveFromUser = new LoveFromUser();
        loveFromUser.setId(UuidUtil.getUUID());
        loveFromUser.setType((byte) Tips.ARTICLE_THUMB.getCode().intValue());
        loveFromUser.setStatus(true);
        loveFromUser.setContentId(null);
        loveFromUser.setFromUserId(userId);
        loveFromUser.setCreateTime(new Date());
        loveFromUser.setUpdateTime(new Date());
        loveFromUser.setFromId(love.getFromId());
        loveFromUserMapper.insert(loveFromUser);

    }
}
