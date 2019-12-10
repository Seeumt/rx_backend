package cn.seeumt.service;

import cn.seeumt.dto.PostCommentDTO;
import cn.seeumt.model.PostComment;

import java.util.List;

public interface PostService {
    int sendPost();

    int comment(String postId);

//    PostCommentDTO findAllCommentsOfAPost(String postId, String replyId);

    List<PostComment> findAllCommentsOfAPost(String postId, String replyId);

//    List<PostComment> findAllCommentsByPostId(String postId);



//    PostCommentDTO findAllCommentsOfAPost1(String postId, String replyId);
}
