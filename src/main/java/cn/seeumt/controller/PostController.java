package cn.seeumt.controller;

import cn.seeumt.dataobject.Post;
import cn.seeumt.dto.PostDTO;
import cn.seeumt.dto.PostListDataItem;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import cn.seeumt.service.CommentService;
import cn.seeumt.service.FollowService;
import cn.seeumt.service.PostService;
import cn.seeumt.utils.ThumberUtil;
import cn.seeumt.utils.TreeUtil;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FollowService followService;


    @GetMapping("/{postId}")
    public ResultVO get(@PathVariable("postId") String postId) {
        if ("".equals(postId)) {
            return ResultVO.error(TipsFlash.NULL_ARGUMENT);
        }
        return postService.get(postId);
    }

    @GetMapping("/")
    public ResultVO list(@RequestParam(value = "userId", defaultValue = "\"\"", required = false) String userId) {
        List<PostListDataItem> postListDataItems = postService.listFollowAndRecommendData(userId);
        return ResultVO.success(postListDataItems);
    }

    @GetMapping("/{type}/{userId}")
    public ResultVO listAlone(@PathVariable("type") Integer type, @PathVariable("userId") String userId) {
        if (!"".equals(userId)) {
            if (type == 0) {
                return postService.listFollowList(userId);
            } else if (type == 1) {
                return postService.listNotFollowList(userId);
            }
            return ResultVO.error(0, "参数错误！");
        }
        return ResultVO.error(0, "用户id不能为空！");
    }



    @PostMapping("/")
    public int send() {
        return postService.sendPost();
    }

    @PostMapping("/detail")
    public ResultVO find(String userId) {
        Post post = postService.selectByUserId(userId);
        return ResultVO.success(post);
    }

    @GetMapping("/find")
    public List<Comment> findAllCommentsNew(String parentId) {
        List<Comment> levelCommentsList = commentService.findNextLevelCommentsByParentId(parentId);
        List<Comment> comments = TreeUtil.listToTree(levelCommentsList, parentId);
        return comments;
    }


    @PostMapping("/findPost")
    public ResultVO findAPost(String postId) {
        PostDTO postDTO = postService.selectByPostId(postId);
        List<Comment> levelCommentsList = commentService.findNextLevelCommentsByParentId(postId);
        List<Comment> comments = TreeUtil.listToTree(levelCommentsList, postId);
        postDTO.setComments(comments);
        List<Thumber> thumbers = ThumberUtil.allThumbers(postId);
        postDTO.setThumbers(thumbers);
        return ResultVO.success(postDTO);
    }











}
