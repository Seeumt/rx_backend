package cn.seeumt.service.impl;

import cn.seeumt.dao.CommentFromUserMapper;
import cn.seeumt.dao.CommentMapper;
import cn.seeumt.dao.ContentMapper;
import cn.seeumt.dataobject.Article;
import cn.seeumt.dataobject.Comment;
import cn.seeumt.dataobject.CommentFromUser;
import cn.seeumt.dataobject.Content;
import cn.seeumt.model.CommentContent;
import cn.seeumt.service.ArticleService;
import cn.seeumt.service.CommentService;
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
    private ArticleService articleService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentFromUserMapper commentFromUserMapper;
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Comment selectByCommentId(String commentId) {
        return commentMapper.selectByCommentId(commentId);
    }

    @Override
    public List<CommentContent> findUserCommentsOfAnArticle(String articleId, String userId) {
        Article article = articleService.selectByPrimaryKey(articleId);
        List<CommentFromUser> commentFromUsersLists = commentFromUserMapper.selectByUserIdAndCommentId(userId, article.getCommentId());
        List<CommentContent> commentContents = new ArrayList<>();
        for (CommentFromUser commentFromUser : commentFromUsersLists) {
            CommentContent commentContent = new CommentContent();
            BeanUtils.copyProperties(commentFromUser,commentContent);
            Content content = contentMapper.selectByContentId(commentFromUser.getContentId());
            commentContent.setContent(content.getContent());
            commentContent.setCommentId(content.getCommentId());
            commentContents.add(commentContent);
        }
        return commentContents;
    }

}
