package cn.seeumt.controller;
import java.util.List;

import cn.seeumt.model.Comment;
import cn.seeumt.service.CommentService;
import cn.seeumt.utils.OnlineUtil;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Seeumt
 * @date 2019/12/9 9:21
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{parentId}")
    public ResultVO queryComment(@PathVariable String parentId) {
        List<Comment> comments = commentService.findNextLevelCommentsByParentId(parentId);
        return ResultVO.success(comments);
    }

    @GetMapping("/")
    public int commentForComment(String commentId,
                        String apiRootId,
                        @RequestParam(value = "type",defaultValue ="3" ) Byte type,
                        String userId,String content) {
       return commentService.comment(apiRootId, userId, content, type, commentId);
    }


    @GetMapping("/root")
    public int commentForRoot(String apiRootId,
                              @RequestParam(value = "type",defaultValue ="3" ) Byte type,
                              String userId,String content) {
        return commentService.commentForRoot(apiRootId, userId, content, type);

    }





}
