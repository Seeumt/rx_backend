package cn.seeumt.service.impl;
import java.util.Date;

import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.dao.CommentMapper;
import cn.seeumt.dao.LoveMapper;
import cn.seeumt.dataobject.Article;
import cn.seeumt.dataobject.Comment;
import cn.seeumt.dataobject.Love;
import cn.seeumt.enums.Tips;
import cn.seeumt.service.ArticleService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seeumt
 * @date 2019/12/8 12:07
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private LoveMapper loveMapper;
    @Autowired
    private CommentMapper commentMapper;
//    @Autowired
//    private ContentMapper contentMapper;

    @Override
    public Article createArticle(Article article) {
        articleMapper.insert(article);
        String loveId = article.getLoveId();
        for (int i = 0; i < 2; i++) {
            Love love = new Love();
            love.setId(UuidUtil.getUUID());
            love.setLoveId(loveId);
            love.setStatus(true);
            love.setCreateTime(new Date());
            love.setUpdateTime(new Date());
            love.setEnabled(true);
            love.setType((byte) (i+1));
            String fromId = UuidUtil.getUUID();
            love.setFromId(fromId);
            loveMapper.insert(love);
        }
        String commentId = article.getCommentId();
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setType((byte) Tips.ARTICLE_COMMENT.getCode().intValue());
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setEnabled(true);
        comment.setId(UuidUtil.getUUID());
        comment.setFromId(UuidUtil.getUUID());
        commentMapper.insert(comment);


        return article;
    }

    @Override
    public Article selectByPrimaryKey(String articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

}
