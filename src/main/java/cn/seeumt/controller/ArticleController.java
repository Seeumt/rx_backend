package cn.seeumt.controller;


import cn.seeumt.dataobject.Article;
import cn.seeumt.dto.ArticleDTO;
import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import cn.seeumt.service.*;
import cn.seeumt.utils.ThumberUtil;

import cn.seeumt.utils.TreeUtil;
import cn.seeumt.vo.CityVO;
import cn.seeumt.vo.ResultVO;
import cn.seeumt.vo.TagVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Seeumt
 * @since 2019-12-21
 */
@RestController
@RequestMapping("/articles")
@Slf4j
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MediaTagsService mediaTagsService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleCitiesService articleCitiesService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/{articleId}")
    public ResultVO findAnArticle(@PathVariable String articleId) {
        return articleService.selectById(articleId);
    }

    @PostMapping("/test")
    public List<Comment> tree(String apiRootId) {
        //找到根评论
        List<Comment> levelCommentsList = commentService.findNextLevelCommentsByParentId(apiRootId);
        List<Comment> comments = TreeUtil.listToTree(levelCommentsList, apiRootId);
        return comments;
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_STU')")
    public ResultVO article(String userId) {
        List<Article> articles = articleService.query(userId);
        return ResultVO.success(articles);
    }

    @PostMapping(value = "/no", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_STU')")
    public ResultVO article(@RequestParam(value = "keywords",required = false,defaultValue = "") String keywords,
                            @RequestParam(value = "page",required = false,defaultValue = "1") int num,
                            @RequestParam(value = "pageSize",required = false,defaultValue = "3")int size) {
        PageInfo<Article> articlePageInfo = articleService.queryAll(num, size,keywords);
        return ResultVO.success(articlePageInfo);
    }


//    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ROLE_STU')")
//    public ResultVO post(@RequestParam(value = "keywords",required = false,defaultValue = "") String keywords,
//                            @RequestParam(value = "page",required = false,defaultValue = "1") int num,
//                            @RequestParam(value = "pageSize",required = false,defaultValue = "3")int size) {
//        PageInfo<Article> articlePageInfo = articleService.queryAll(num, size,keywords);
//        return ResultVO.success(articlePageInfo);
//    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO post(@RequestBody cn.seeumt.form.Article article) throws HttpException {
        return articleService.insert(article);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO search(@RequestParam(value = "keywords", defaultValue = "",required = false) String keywords,
                           @RequestParam(value = "currentNum") int currentNum,
                           @RequestParam(value = "size",required = false,defaultValue = "7") int size) {
        log.info("通过{}搜索文章",keywords);
        return articleService.search(keywords,currentNum,size);
    }


    }
