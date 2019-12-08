package cn.seeumt.dao;

import cn.seeumt.dataobject.CommentFromUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentFromUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommentFromUser record);

    int insertSelective(CommentFromUser record);

    CommentFromUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommentFromUser record);

    int updateByPrimaryKey(CommentFromUser record);

    List<CommentFromUser> selectByUserIdAndCommentId(@Param("userId") String userId, @Param("commentId") String commentId);

    List<CommentFromUser> selectByCommentId(String commentId);


}
