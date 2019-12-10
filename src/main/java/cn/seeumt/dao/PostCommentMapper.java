package cn.seeumt.dao;

import cn.seeumt.dataobject.PostComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(PostComment record);

    int insertSelective(PostComment record);

    PostComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PostComment record);

    int updateByPrimaryKey(PostComment record);

    List<PostComment> findAllCommentsOfAPostByPostIdAndReplyId(@Param("postId") String postId, @Param("replyId") String replyId);

    List<PostComment> selectByCommentId(String commentId);

    List<PostComment> selectByReplyId(String replyId);


    List<PostComment> findAllCommentsByPostId(String postId);
}
