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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章
 * @author Seeumt
 * @since 2019-12-21
 */
@Api(tags = {"文章"})
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

    /**
     * 查询某篇文章
     * @param articleId 文章主键
     * @return ResultVO
     */
    @GetMapping("/{articleId}")
    @ApiOperation(value = "查询某篇文章",notes = "根据文章主键查询")
    public ResultVO findAnArticle(@PathVariable String articleId) {
        return articleService.selectById(articleId);
    }

    /**
     * 查询文章的所有评论（树状）
     * @param apiRootId 文章主键id
     * @return List<Comment>
     */
    @PostMapping("/test")
    public List<Comment> tree(String apiRootId) {
        //找到根评论
        List<Comment> levelCommentsList = commentService.findNextLevelCommentsByParentId(apiRootId);
        List<Comment> comments = TreeUtil.listToTree(levelCommentsList, apiRootId);
        return comments;
    }

    /**
     * 查询某一用户的所有文章
     * @param userId 用户主键id
     * @return ResultVO
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_STU')")
    public ResultVO article(String userId) {
        List<Article> articles = articleService.query(userId);
        return ResultVO.success(articles);
    }

    /**
     * 分页根据关键此查询文章
     * @param keywords 关键词
     * @param num 当前页
     * @param size 每页条数
     * @return ResultVO
     */
    @PostMapping(value = "/no", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_STU')")
    public ResultVO article(@RequestParam(value = "keywords",required = false,defaultValue = "") String keywords,
                            @RequestParam(value = "page",required = false,defaultValue = "1") int num,
                            @RequestParam(value = "pageSize",required = false,defaultValue = "3")int size) {
        PageInfo<Article> articlePageInfo = articleService.queryAll(num, size,keywords);
        return ResultVO.success(articlePageInfo);
    }

    /**
     * 发布文章
     * @param article 文章请求体
     * @return ResultVO
     * @throws HttpException
     */
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO post(@RequestBody cn.seeumt.form.Article article) throws HttpException {
        return articleService.insert(article);
    }

    /**
     * 分页根据关键此查询文章
     * @param keywords 关键词
     * @param currentNum 当前页
     * @param size 每页条数
     * @return ResultVO
     */
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO search(@RequestParam(value = "keywords", defaultValue = "",required = false) String keywords,
                           @RequestParam(value = "currentNum") int currentNum,
                           @RequestParam(value = "size",required = false,defaultValue = "7") int size) {
        log.info("通过{}搜索文章",keywords);
        return articleService.search(keywords,currentNum,size);
    }


    }
