package cn.seeumt.dao;

import cn.seeumt.dataobject.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 通过主键删除
     * @param commentId
     * @return
     */
    int deleteByPrimaryKey(String commentId);

    @Override
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
