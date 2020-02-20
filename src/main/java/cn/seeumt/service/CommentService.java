package cn.seeumt.service;

import cn.seeumt.dataobject.Comment;
import cn.seeumt.model.CommentContent;
import cn.seeumt.vo.*;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 16:17
 */
public interface CommentService {

    List<Comment> getAllLuckyComments(String apiRootId);

    ResultVO listByParentId(String parentId);


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
     * @param parentId 要评论的评论的id 作为其子评论的parentId
     * @return 1
     */
    ResultVO comment(String apiRootId, String userId, String content, Byte type, String parentId);
//    ResultVO comment( String userId, String content, Byte type, String commentId,String parentId);

    CommentFirstMO getLuckyCommentsAndChildren(String apiRootId);

    /**
     * 通过某parentId(父级Id 第一级评论的父级id都是articleId,postId)
     * 找到其所有下一级的评论    !!! 一般都是从第一级开始找）
     * 并对返回对象进行组装
     * @param parentId（第一级评论的父级id都是articleId,postId）
     * @return model Comment
     */
    List<cn.seeumt.model.Comment> findNextLevelCommentsByParentId(String parentId);



    List<CommentVO> findAllLevelCommentsByApiRootId(String apiRootId);


    /**
     * 通过根id 找到属于其的子评论数字
     * @param rootId
     * @param type
     * @return
     */
    List<Comment> selectCommentCountByRootIdAndType(String rootId, Byte type);

    List<Comment> selectCommentCountByParentIdAndType(String parentId, Byte type);

    List<CommentFirstVO> queryHomeComments(String apiRootId);

    List<Comment> queryOneLevelComments(String apiRootId);

    List<CommentFirstVO> queryHomeAndAllComments(String apiRootId);

    List<CommentVO> getLuckyChildData(String apiRootId);
}

