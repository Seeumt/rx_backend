package cn.seeumt.controller;

import cn.seeumt.dao.*;
import cn.seeumt.dataobject.*;
import cn.seeumt.dto.ArticleDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.model.CommentContent;
import cn.seeumt.model.Commenter;
import cn.seeumt.service.ArticleService;
import cn.seeumt.service.CommentService;
import cn.seeumt.service.UserInfoService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentFromUserMapper commentFromUserMapper;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ArticleMapper articleMapper;


    @GetMapping(value = "/")
    @ResponseBody
    public Article add() {
        Article article = new Article();
        String id = UuidUtil.getUUID();
        article.setId(id);
        article.setTitle("第四篇文章");
        article.setMdContent("第四篇文章的mdcontent");
        article.setHtmlContent("第四篇文章的htmlcontent");
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
        List<CommentFromUser> commentFromUserList = commentFromUserMapper.selectByCommentId(article.getCommentId());
        Set set = new HashSet();
        for (CommentFromUser commentFromUser : commentFromUserList) {
            set.add(commentFromUser.getFromUserId());
        }
        List<Commenter> commenters = new ArrayList<>();
        for (int i = 0; i < set.toArray().length; i++) {
            Commenter commenter = new Commenter();
            commenter.setUserId(set.toArray()[i].toString());
            UserInfo userInfo = userInfoService.selectByPrimaryKey(set.toArray()[i].toString());
            commenter.setNickname(userInfo.getNickname());
            commenter.setFaceIcon(userInfo.getFaceIcon());
            commenter.setCommentContents(commentService.findUserCommentsOfAnArticle(articleId, set.toArray()[i].toString()));
            commenters.add(commenter);
        }
        articleDTO.setCommenters(commenters);


        return articleDTO;

    }

    @GetMapping(value = "/comment")
    public void comment(String articleId,String userId, String commentContent) {
        Article article = articleService.selectByPrimaryKey(articleId);
        Comment comment = commentService.selectByCommentId(article.getCommentId());
        String commentId = comment.getCommentId();
        CommentFromUser commentFromUser = new CommentFromUser();
        commentFromUser.setId(UuidUtil.getUUID());
        commentFromUser.setType((byte) Tips.ARTICLE_COMMENT.getCode().intValue());
        commentFromUser.setCreateTime(new Date());
        commentFromUser.setUpdateTime(new Date());
        //这个commentId 每篇文章只有一个
        commentFromUser.setCommentId(commentId);
        commentFromUser.setFromId(comment.getFromId());
        commentFromUser.setFromUserId(userId);
        commentFromUser.setContentId(UuidUtil.getUUID());
        commentFromUserMapper.insert(commentFromUser);
        Content content = new Content();
        content.setId(UuidUtil.getUUID());
        content.setType((byte) Tips.ARTICLE_COMMENT.getCode().intValue());
        content.setCreateTime(new Date());
        content.setUpdateTime(new Date());
        content.setDeleted(false);
        content.setLoveId(UuidUtil.getUUID());
        content.setCommentId(UuidUtil.getUUID());
        content.setContentId(commentFromUser.getContentId());
        content.setContent(commentContent);
        contentMapper.insert(content);

    }



}
