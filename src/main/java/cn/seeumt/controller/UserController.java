package cn.seeumt.controller;

import cn.seeumt.form.UserInfo;
import cn.seeumt.model.CommentContent;
import cn.seeumt.service.CommentService;
import cn.seeumt.service.UserInfoService;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 18:08
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CommentService commentService;
    @GetMapping(value = "/")
    public List<CommentContent> findMyCommentsOfAnArticle(String articleId,String userId) {
        return commentService.findUserCommentsOfAnArticle(articleId, userId);
    }

    @PostMapping(value = "/registerOrLogin",consumes = MediaType.APPLICATION_JSON_VALUE)
//    @Cacheable(cacheNames = "user_session",key = "123456")
    // 这是把这个resultVO放到了redis里？
    public ResultVO login(@RequestBody UserInfo userInfo) {
        ResultVO resultVO = userInfoService.logIn(userInfo.getUserId(), userInfo.getPassword());
        return resultVO;
    }


    @GetMapping("/logout")
    public ResultVO logout(HttpSession httpSession) {
        httpSession.removeAttribute(httpSession.getId());
        return ResultVO.success();
    }
//
//    @GetMapping("/registerOrLogin")
//    public ResultVO registerOrLogin(@RequestBody UserInfo userInfo) {
//        ResultVO resultVO = userInfoService.logIn(userInfo.getUsername(), userInfo.getPassword());
//        if (resultVO.getCode() == 0) {
//            httpSession.setAttribute(httpSession.getId(), resultVO.getData());
//        }
//        return resultVO;
//    }



}
