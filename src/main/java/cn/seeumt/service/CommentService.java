package cn.seeumt.service;

import cn.seeumt.dataobject.Comment;

/**
 * @author Seeumt
 * @date 2019/12/8 16:17
 */
public interface CommentService {
    Comment selectByCommentId(String commentId);
}

