package cn.seeumt.controller;
import java.util.List;

import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.form.Comment;
import cn.seeumt.service.CommentService;
import cn.seeumt.utils.OnlineUtil;
import cn.seeumt.vo.CommentVO;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论
 * @author Seeumt
 * @date 2019/12/9 9:21
 */
@Api(tags = {"评论"})
@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 查询子评论 树状
     * @param parentId 父级某id
     * @return ResultVO
     */
    @GetMapping("/child")
    public ResultVO queryChildComment(String parentId) {
        return commentService.listByParentId(parentId);
    }


    /**
     * 查询推荐评论
     * @param apiRootId 某主键id
     * @param currentNum 当前页码
     * @param size 某页条数
     * @return ResultVO
     */
    @GetMapping("/")
    public ResultVO queryHomeComments(String apiRootId,
                                    @RequestParam(value = "currentNum") int currentNum,
                                    @RequestParam(value = "size", required = false, defaultValue = "5") int size){
        return ResultVO.success(commentService.getLuckyCommentsAndChildren(apiRootId,currentNum,size));
    }


    /**
     * 分页查询子评论
     * @param apiRootId 某主键id
     * @param currentNum 当前页码
     * @param size 每页条数
     * @return ResultVO
     */
    @GetMapping("/{apiRootId}")
    public ResultVO getLuckyDetail(@PathVariable String apiRootId,
                                   @RequestParam(value = "currentNum") int currentNum,
                                   @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        return commentService.getLuckyChildData(apiRootId,currentNum,size);
    }


    /**
     * 为某条评论评论
     * @param comment 评论请求体
     * @return ResultVO
     * @throws HttpException
     */
    @PostMapping("/")
    public ResultVO commentForComment(@RequestBody Comment comment) throws HttpException {
        OnlineUtil.setLastOperateTimeByUserId(comment.getUserId());
        log.info("【评论】用户 {}评论 {}:{}",comment.getUserId(),comment.getParentId(),comment.getContent());
        return commentService.comment(comment.getApiRootId(), comment.getUserId(), comment.getContent(), comment.getType(),comment.getParentId());
    }


    /**
     * 为root评论
     * @param apiRootId 某主键id
     * @param type 评论类型
     * @param userId 用户主键id
     * @param content 用户评论内容
     * @return int
     */
    @GetMapping("/root")
    public int commentForRoot(String apiRootId,
                              @RequestParam(value = "type",defaultValue ="3" ) Byte type,
                              String userId,String content) {
        OnlineUtil.setLastOperateTimeByUserId(userId);
        log.info("【评论】用户 {}评论 {}:{}",userId,apiRootId,content);
        return commentService.commentForRoot(apiRootId, userId, content, type);

    }





}
