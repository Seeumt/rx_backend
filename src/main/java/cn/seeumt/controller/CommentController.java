package cn.seeumt.controller;
import java.util.Date;

import cn.seeumt.dao.*;

import cn.seeumt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Seeumt
 * @date 2019/12/9 9:21
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public int comment(String commentId,
                        String postId,
                        @RequestParam(value = "type",defaultValue ="6" ) Byte type,
                        String userId,String content) {
       return commentService.comment(postId, userId, content, type, commentId);

    }

//    @GetMapping("/add")
//    public void addLoveToComments(String userId,String contentId) {
//
//    }
}
