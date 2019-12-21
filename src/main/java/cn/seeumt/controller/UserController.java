package cn.seeumt.controller;

import cn.seeumt.model.CommentContent;
import cn.seeumt.service.CommentService;
import cn.seeumt.service.UserInfoService;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 18:08
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CommentService commentService;
    @GetMapping(value = "/")
    public List<CommentContent> findMyCommentsOfAnArticle(String articleId,String userId) {
        return commentService.findUserCommentsOfAnArticle(articleId, userId);
    }

    @GetMapping("/login")
//    @Cacheable(cacheNames = "user_session",key = "123456")
    // 这是把这个resultVO放到了redis里？
    public ResultVO login(String userId, String password, HttpSession httpSession) {
        ResultVO resultVO = userInfoService.logIn(userId, password);
        if (resultVO.getCode() == 0) {
            httpSession.setAttribute(httpSession.getId(), resultVO.getData());
        }
        return resultVO;
    }


    @GetMapping("/logout")
    public ResultVO logout(HttpSession httpSession) {
        httpSession.removeAttribute(httpSession.getId());
        return ResultVO.success();
    }



}
