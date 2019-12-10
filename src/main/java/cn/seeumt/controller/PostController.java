package cn.seeumt.controller;

import cn.seeumt.dao.PostCommentMapper;
import cn.seeumt.dto.PostCommentDTO;
import cn.seeumt.model.PostComment;
import cn.seeumt.service.PostCommentService;
import cn.seeumt.service.PostService;
import cn.seeumt.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {


    @Autowired
    private PostService postService;
    @Autowired
    private PostCommentService postCommentService;

    @GetMapping("/comments")
    public List<PostComment> findAllComments(String postId) {
        List<PostComment> allCommentsOfAPost = postCommentService.findAllCommentsByPostId(postId);
        List<PostComment> postComments = TreeUtil.listToTree(allCommentsOfAPost,postId);
        return postComments;
    }


    @GetMapping("/comment")
    public int comment(String postId) {
        return postService.comment(postId);
    }

    @GetMapping("/")
    public int send() {
        return postService.sendPost();
    }






}
