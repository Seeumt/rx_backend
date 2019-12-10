package cn.seeumt.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.seeumt.dao.PostCommentMapper;
import cn.seeumt.dataobject.PostComment;
import cn.seeumt.enums.Tips;
import cn.seeumt.service.PostCommentService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommentServiceImpl implements PostCommentService {
    @Autowired
    private PostCommentMapper postCommentMapper;
    @Override
    public int comment(String postId, String replyId,String userId) {
        PostComment postComment = new PostComment();
        postComment.setId(UuidUtil.getUUID());
        postComment.setType((byte) Tips.POST_COMMENT_COMMENT.getCode().intValue());
        postComment.setCreateTime(new Date());
        postComment.setUpdateTime(new Date());
        postComment.setEnabled(false);
        postComment.setReplyId(replyId);
        postComment.setPostId(postId);
        postComment.setUserId(userId);
        return postCommentMapper.insert(postComment);
    }

    @Override
    public List<cn.seeumt.model.PostComment> selectByReplyId(String replyId) {
        List<PostComment> commentsLevelOne = postCommentMapper.selectByReplyId(replyId);
        List<cn.seeumt.model.PostComment> postComments = new ArrayList<>();
        for (PostComment postComment : commentsLevelOne) {
            cn.seeumt.model.PostComment postComment1 = new cn.seeumt.model.PostComment();
            BeanUtils.copyProperties(postComment,postComment1);
            postComments.add(postComment1);
        }
        return postComments;
    }

    @Override
    public List<cn.seeumt.model.PostComment> findAllCommentsByPostId(String postId) {
        List<PostComment> commentsLevelOne = postCommentMapper.findAllCommentsByPostId(postId);
        List<cn.seeumt.model.PostComment> postComments = new ArrayList<>();
        for (PostComment postComment : commentsLevelOne) {
            cn.seeumt.model.PostComment postComment1 = new cn.seeumt.model.PostComment();
            BeanUtils.copyProperties(postComment,postComment1);
            postComments.add(postComment1);
        }
        return postComments;
    }

}
