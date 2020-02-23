package cn.seeumt.dao;

import cn.seeumt.dataobject.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author Seeumt
 */
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 通过主键删除
     * @param commentId 评论id
     * @return
     */
    int deleteByPrimaryKey(String commentId);

    @Override
    /***
     * 插入记录
     * @param record
     * @return int
     */
    int insert(Comment record);

    /**
     * 选择性插入
     * @param record
     * @return int
     */
    int insertSelective(Comment record);

    /**
     * 通过通过主键选择
     * @param commentId
     * @return
     */
    Comment selectByPrimaryKey(String commentId);

    /**
     * 选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * 通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Comment record);

    /**
     * 通过某parentId(父级Id 第一级评论的父级id都是postId )
     * 找到其所有下一级的评论的集合
     * @param parentId
     * @return model Comment
     */
    List<Comment> findNextLevelCommentsByParentId(String parentId);
}
