package cn.seeumt.service;

import cn.seeumt.dataobject.Comment;
import cn.seeumt.model.CommentContent;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 16:17
 */
public interface CommentService {
    Comment selectByCommentId(String commentId);
    List<CommentContent> findUserCommentsOfAnArticle(String articleId, String userId);
    List<CommentContent> findUserCommentsOfAnComments(String userId,String commentId);

    /**
     *插入一级评论
     * @param apiRootId 要评论的post的post_id、要评论的article的article_id 这类根id
     * @param userId 评论者的user_id
     * @param content 评论者的评论内容
     * @param type 评论的类型 文章的评论 文章评论的评论 动态的评论 动态评论的评论
     * @return 1
     */
    int commentForRoot(String apiRootId,String userId,String content,Byte type);


    /**
     *插入非一级评论
     * @param apiRootId 要评论的post的post_id、要评论的article的article_id 这类根id
     * @param userId 评论者的user_id
     * @param content 评论者的评论内容
     * @param type 评论的类型 文章的评论 文章评论的评论 动态的评论 动态评论的评论
     * @param commentId 要评论的评论的id 作为其子评论的parentId
     * @return 1
     */
    int comment(String apiRootId,String userId,String content,Byte type,String commentId);

    /**
     * 通过某parentId(父级Id 第一级评论的父级id都是postId)
     * 找到其所有下一级的评论
     * 并对返回对象进行组装
     * @param parentId
     * @return model Comment
     */
    List<cn.seeumt.model.Comment > findNextLevelCommentsByParentId(String parentId);

}

