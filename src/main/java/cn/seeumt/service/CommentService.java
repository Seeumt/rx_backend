package cn.seeumt.service;

import cn.seeumt.dataobject.Comment;
import cn.seeumt.vo.*;
import org.apache.http.HttpException;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 16:17
 */
public interface CommentService {

    /**
     * 获取所有0级评论
     * @param apiRootId 根id(文章id,动态id)
     * @return ResultVO
     */
    List<Comment> getAllLuckyComments(String apiRootId);

    /**
     * 查询子级评论VO
     * @param parentId
     * @return ResultVO
     */
    ResultVO listByParentId(String parentId);


    /**
     * 查询评论
     * @param commentId 评论id
     * @return Comment
     */
    Comment selectByCommentId(String commentId);


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
    ResultVO comment(String apiRootId, String userId, String content, Byte type, String parentId) throws HttpException;

    /**
     * 查询所有一级评论及其下所有评论
     * @param apiRootId 文章id/动态id
     * @param currentNum 当前页
     * @param size  每页条数
     * @return CommentFirstMo
     */
    CommentFirstMo getLuckyCommentsAndChildren(String apiRootId,int currentNum,int size);

    /**
     * 通过某parentId(父级Id 第一级评论的父级id都是articleId,postId)
     * 找到其所有下一级的评论    !!! 一般都是从第一级开始找）
     * 并对返回对象进行组装
     * @param parentId（第一级评论的父级id都是articleId,postId）
     * @return model Comment
     */
    List<cn.seeumt.model.Comment> findNextLevelCommentsByParentId(String parentId);


    /**
     * 找到所有级别的评论（*）
     * @param apiRootId
     * @return List<CommentVO>
     */
    List<CommentVO> findAllLevelCommentsByApiRootId(String apiRootId);


    /**
     * 通过根id 找到属于其的子评论数字
     * @param rootId 任意一条评论
     * @param type 类型
     * @return List<Comment>
     */
    List<Comment> selectCommentCountByRootIdAndType(String rootId, Byte type);

    /**
     * 通过根id 找到属于其的子评论数字
     * @param parentId 任意一条评论
     * @param type 类型
     * @return List<Comment>
     */
    List<Comment> selectCommentCountByParentIdAndType(String parentId, Byte type);

    /**
     * 查询所有一级评论及其省略评论
     * @param apiRootId
     * @return
     */
    List<CommentFirstVO> queryHomeComments(String apiRootId);

    /**
     * 查询零级评论（*）
     * @param apiRootId
     * @return List<Comment>
     */
    List<Comment> queryOneLevelComments(String apiRootId);

    /**
     * 查询所有评论数
     * @param apiRootId
     * @return
     */
    Integer getAllCommentCount(String apiRootId);

    /**
     * 根据0级评论查询其下所有评论
     * @param apiRootId 0级评论id
     * @param currentNum 当前页
     * @param size  每页条数
     * @return List<CommentVO>
     */
    ResultVO getLuckyChildData(String apiRootId, int currentNum, int size);
}

