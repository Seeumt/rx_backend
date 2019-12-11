package cn.seeumt.service;

import cn.seeumt.model.PostComment;

import java.util.List;

public interface PostCommentService {
    int comment(String postId, String replyId,String userId);

    List<PostComment> selectByReplyId(String replyId);

    /**
     * 通过某postId(其实是父级Id 因为第一级评论的父级id都是postId)找到其所有下一级的评论
     * @param postId
     * @return
     */
    List<PostComment> findAllCommentsByPostId(String postId);
}
