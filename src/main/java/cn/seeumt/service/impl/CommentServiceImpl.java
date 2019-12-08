package cn.seeumt.service.impl;

import cn.seeumt.dao.CommentMapper;
import cn.seeumt.dataobject.Comment;
import cn.seeumt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seeumt
 * @date 2019/12/8 16:17
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Comment selectByCommentId(String commentId) {
        return commentMapper.selectByCommentId(commentId);
    }

}
