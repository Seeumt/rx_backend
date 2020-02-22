package cn.seeumt.controller;
import java.util.List;

import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.form.Comment;
import cn.seeumt.service.CommentService;
import cn.seeumt.utils.OnlineUtil;
import cn.seeumt.vo.CommentVO;
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

    @GetMapping("/child")
    public ResultVO queryChildComment(String parentId) {
        return commentService.listByParentId(parentId);
    }



    @GetMapping("/")
    public ResultVO queryHomeComments(String apiRootId) {
        return ResultVO.success(commentService.getLuckyCommentsAndChildren(apiRootId));
    }


    @GetMapping("/{apiRootId}")
    public ResultVO getLuckyDetail(@PathVariable String apiRootId) {
        return ResultVO.success(commentService.getLuckyChildData(apiRootId));
    }


    @PostMapping("/")
    public ResultVO commentForComment(@RequestBody Comment comment) {
        return commentService.comment(comment.getApiRootId(), comment.getUserId(), comment.getContent(), comment.getType(),comment.getParentId());
    }


    @GetMapping("/root")
    public int commentForRoot(String apiRootId,
                              @RequestParam(value = "type",defaultValue ="3" ) Byte type,
                              String userId,String content) {
        return commentService.commentForRoot(apiRootId, userId, content, type);

    }





}
