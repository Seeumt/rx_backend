package cn.seeumt.service;

import cn.seeumt.model.PostComment;

import java.util.List;

public interface PostCommentService {
    int comment(String postId, String replyId,String userId);

    List<PostComment> selectByReplyId(String replyId);

    List<PostComment> findAllCommentsByPostId(String postId);
}
