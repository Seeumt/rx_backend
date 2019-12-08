package cn.seeumt.dao;

import cn.seeumt.dataobject.CommentFromUser;

public interface CommentFromUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommentFromUser record);

    int insertSelective(CommentFromUser record);

    CommentFromUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommentFromUser record);

    int updateByPrimaryKey(CommentFromUser record);
}