package cn.seeumt.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.seeumt.dao.PostCommentMapper;
import cn.seeumt.dao.PostMapper;
import cn.seeumt.dataobject.Post;
import cn.seeumt.dataobject.PostComment;
import cn.seeumt.dto.PostCommentDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.service.PostService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostCommentMapper postCommentMapper;

    List<PostComment> postComments1 = new ArrayList<>();

    @Override
    public List<cn.seeumt.model.PostComment> findAllCommentsOfAPost(String postId, String replyId) {

        List<PostComment> commentsLevelOne = postCommentMapper.findAllCommentsOfAPostByPostIdAndReplyId(postId, replyId);
        List<cn.seeumt.model.PostComment> postComments = new ArrayList<>();
        for (PostComment postComment : commentsLevelOne) {
            cn.seeumt.model.PostComment postComment1 = new cn.seeumt.model.PostComment();
            BeanUtils.copyProperties(postComment,postComment1);
            postComments.add(postComment1);
        }
        return postComments;
    }


    @Override
    public int comment(String postId) {
        PostComment postComment = new PostComment();
        postComment.setId(UuidUtil.getUUID());
        postComment.setType((byte) Tips.POST_COMMENT.getCode().intValue());
        postComment.setCreateTime(new Date());
        postComment.setUpdateTime(new Date());
        postComment.setEnabled(false);
        postComment.setReplyId(postId);
        postComment.setPostId(postId);
        postComment.setUserId("Tips");
        return postCommentMapper.insert(postComment);
    }


    @Override
    public int sendPost() {
        Post post = new Post();
        post.setPostId(UuidUtil.getUUID());
        post.setType(true);
        post.setContentId(UuidUtil.getUUID());
        post.setImgId(UuidUtil.getUUID());
        post.setLoveId(UuidUtil.getUUID());
        post.setUserId("Seeumt");
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());
        post.setDeleted(false);
        return postMapper.insert(post);
    }
}
