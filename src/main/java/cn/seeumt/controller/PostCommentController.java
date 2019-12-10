package cn.seeumt.controller;

import cn.seeumt.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postComments")
public class PostCommentController {
    @Autowired
    private PostCommentService postCommentService;
    @GetMapping("/comments")
    public int comment(String postId, @RequestParam("commentId") String replyId, String userId) {
        int i = postCommentService.comment(postId,replyId,userId);
        return i;
    }
}
