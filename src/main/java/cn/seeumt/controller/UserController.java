//package cn.seeumt.controller;
//
//import cn.seeumt.model.CommentContent;
//import cn.seeumt.service.ArticleService;
//import cn.seeumt.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * @author Seeumt
// * @date 2019/12/8 18:08
// */
//@RestController
//@RequestMapping("/user")
//public class UserController {
//    @Autowired
//    private ArticleService articleService;
//    @Autowired
//    private CommentFromUserMapper commentFromUserMapper;
//    @Autowired
//    private ContentMapper contentMapper;
//    @Autowired
//    private CommentService commentService;
//    @GetMapping(value = "/")
//    public List<CommentContent> findMyCommentsOfAnArticle(String articleId,String userId) {
//
//        return commentService.findUserCommentsOfAnArticle(articleId, userId);
//
//    }
//}
