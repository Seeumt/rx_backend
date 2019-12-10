//package cn.seeumt.controller;
//import java.util.Date;
//
//import cn.seeumt.dao.*;
//import cn.seeumt.dataobject.*;
//import cn.seeumt.enums.Tips;
//import cn.seeumt.service.ArticleService;
//import cn.seeumt.service.ContentService;
//import cn.seeumt.service.LoveService;
//import cn.seeumt.utils.UuidUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author Seeumt
// * @date 2019/12/9 9:21
// */
//@RestController
//@RequestMapping("/comments")
//public class CommentController {
//    @Autowired
//    private CommentMapper commentMapper;
//    @Autowired
//    private CommentFromUserMapper commentFromUserMapper;
//    @Autowired
//    private ContentMapper contentMapper;
//    @Autowired
//    private LoveMapper loveMapper;
//    @Autowired
//    private LoveFromUserMapper loveFromUserMapper;
//    @Autowired
//    private ContentService contentService;
//
//    @GetMapping("/")
//    public void addCommentToComments(String userId, String commentId,String commentContent) {
//        Comment comment = new Comment();
//        comment.setId(UuidUtil.getUUID());
//        comment.setType((byte) Tips.COMMENT_COMMENT.getCode().intValue());
//        comment.setCreateTime(new Date());
//        comment.setUpdateTime(new Date());
//        comment.setEnabled(true);
//        comment.setFromId(UuidUtil.getUUID());
//        comment.setCommentId(commentId);
//        commentMapper.insert(comment);
//
//        CommentFromUser commentFromUser = new CommentFromUser();
//        commentFromUser.setId(UuidUtil.getUUID());
//        commentFromUser.setType((byte) Tips.COMMENT_COMMENT.getCode().intValue());
//        commentFromUser.setCreateTime(new Date());
//        commentFromUser.setUpdateTime(new Date());
//        //这个commentId 每条评论只有一个
//        commentFromUser.setCommentId(commentId);
//        commentFromUser.setFromId(comment.getFromId());
//        commentFromUser.setFromUserId(userId);
//        commentFromUser.setContentId(UuidUtil.getUUID());
//        commentFromUserMapper.insert(commentFromUser);
//        Content content = new Content();
//        content.setId(UuidUtil.getUUID());
//        content.setType((byte) Tips.COMMENT_COMMENT.getCode().intValue());
//        content.setCreateTime(new Date());
//        content.setUpdateTime(new Date());
//        content.setDeleted(false);
//        content.setLoveId(UuidUtil.getUUID());
//        content.setCommentId(UuidUtil.getUUID());
//        content.setContentId(commentFromUser.getContentId());
//        content.setContent(commentContent);
//        contentMapper.insert(content);
//
//    }
//
//    @GetMapping("/add")
//    public void addLoveToComments(String userId,String contentId) {
//        Content content = contentService.selectByContentId(contentId);
//        Love love = new Love();
//        love.setId(UuidUtil.getUUID());
//        love.setType((byte) Tips.COMMENT_THUMB.getCode().intValue());
//        love.setStatus(true);
//        love.setCreateTime(new Date());
//        love.setUpdateTime(new Date());
//        love.setEnabled(true);
//        String fromId = UuidUtil.getUUID();
//        love.setFromId(fromId);
//        love.setLoveId(content.getLoveId());
//        loveMapper.insert(love);
//        LoveFromUser loveFromUser = new LoveFromUser();
//        loveFromUser.setId(UuidUtil.getUUID());
//        loveFromUser.setType((byte) Tips.COMMENT_THUMB.getCode().intValue());
//        loveFromUser.setStatus(true);
//        loveFromUser.setContentId(null);
//        loveFromUser.setFromUserId(userId);
//        loveFromUser.setCreateTime(new Date());
//        loveFromUser.setUpdateTime(new Date());
//        loveFromUser.setFromId(love.getFromId());
//        loveFromUserMapper.insert(loveFromUser);
//    }
//}
