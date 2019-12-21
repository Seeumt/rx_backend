package cn.seeumt.service.impl;
import java.util.Date;

import cn.seeumt.dao.CommentMapper;
import cn.seeumt.dataobject.Comment;
import cn.seeumt.model.CommentContent;
import cn.seeumt.service.CommentService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 16:17
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    public static Comment createComment(String apiRootId, String userId, String content,Byte type,String parentId) {
        Comment comment = new Comment();
        comment.setCommentId(UuidUtil.getUUID());
        comment.setType(type);
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setEnabled(true);
        comment.setApiRootId(apiRootId);
        comment.setParentId(parentId);
        return comment;
    }

    @Override
    public List<cn.seeumt.model.Comment> findNextLevelCommentsByParentId(String parentId) {
        List<Comment> commentsSomeLevel = commentMapper.findNextLevelCommentsByParentId(parentId);
        List<cn.seeumt.model.Comment> comments = new ArrayList<>();
        for (Comment comment : commentsSomeLevel) {
            cn.seeumt.model.Comment commentModel = new cn.seeumt.model.Comment();
            BeanUtils.copyProperties(comment,commentModel);
            comments.add(commentModel);
        }
        return comments;
    }

    @Override
    public int commentForRoot(String apiRootId, String userId, String content,Byte type) {
        Comment comment = CommentServiceImpl.createComment(apiRootId, userId, content, type, apiRootId);
        return commentMapper.insert(comment);
    }

    @Override
    public int comment(String apiRootId, String userId, String content, Byte type,String commentId) {
        Comment comment = CommentServiceImpl.createComment(apiRootId, userId, content, type, commentId);
        return commentMapper.insert(comment);
    }



//    @Override
//    public Comment selectByCommentId(String commentId) {
//        return commentMapper.selectByCommentId(commentId);
//    }

    @Override
    public Comment selectByCommentId(String commentId) {
        return null;
    }

    @Override
    public List<CommentContent> findUserCommentsOfAnArticle(String articleId, String userId) {
        return null;
    }

    @Override
    public List<CommentContent> findUserCommentsOfAnComments(String userId, String commentId) {
        return null;
    }


}
