package cn.seeumt.controller;

import cn.seeumt.dao.LoveFromUserMapper;
import cn.seeumt.dao.LoveMapper;
import cn.seeumt.dataobject.Article;
import cn.seeumt.dataobject.Love;
import cn.seeumt.dataobject.LoveFromUser;
import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.dto.ArticleDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.service.ArticleService;
import cn.seeumt.service.UserInfoService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 13:33
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LoveMapper loveMapper;
    @Autowired
    private LoveFromUserMapper loveFromUserMapper;

    @GetMapping(value = "/")
    @ResponseBody
    public Article add() {
        Article article = new Article();
        String id = UuidUtil.getUUID();
        article.setId(id);
        article.setTitle("第一篇文章");
        article.setMdContent("第一篇文章的mdcontent");
        article.setHtmlContent("第一篇文章的htmlcontent");
        String loveId = UuidUtil.getUUID();
        article.setLoveId(loveId);
        String commentId = UuidUtil.getUUID();
        article.setCommentId(commentId);
        String userId = UuidUtil.getUUID();
        article.setUserId(userId);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setEnabled(true);
        article.setDeleted(false);
        articleService.createArticle(article);
        return article;
    }


    @GetMapping(value = "/findArticle")
    @ResponseBody
    public ArticleDTO add(String articleId) {

        Article article = articleService.selectByPrimaryKey(articleId);
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article,articleDTO);
        Love love = loveMapper.selectByLoveIdAndType(article.getLoveId(), Tips.ARTICLE_THUMB.getCode());
        List<LoveFromUser> loveFromUsersList = loveFromUserMapper.selectListByFromId(love.getFromId());
        List<UserInfo> userInfoList = new ArrayList<>();
        for (LoveFromUser loveFromUser : loveFromUsersList) {
            userInfoList.add(userInfoService.selectByPrimaryKey(loveFromUser.getFromUserId()));
        }
        articleDTO.setThumbers(userInfoList);
        return articleDTO;

    }
}
