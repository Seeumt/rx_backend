package cn.seeumt.dao;

import cn.seeumt.dataobject.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(String commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 通过某parentId(父级Id 第一级评论的父级id都是postId )
     * 找到其所有下一级的评论的集合
     * @param parentId
     * @return model Comment
     */
    List<Comment> findNextLevelCommentsByParentId(String parentId);
}
