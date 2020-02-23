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
    public ArticleDTO findAnArticle(@PathVariable String articleId) {
        ArticleDTO articleDTO = new ArticleDTO();
        List<String> tagIds = mediaTagsService.findTagIdsByParentId(articleId);
        List<TagVO> tagVos = tagService.findTagVoByTagIds(tagIds);
        articleDTO.setTagVos(tagVos);
        List<String> cityIds = articleCitiesService.findCityIdsByArticleId(articleId);
        List<CityVO> cityVos = cityService.findByCityIds(cityIds);
        articleDTO.setViaCitiesVos(cityVos);
        //找到根评论
        List<Comment> levelCommentsList = commentService.findNextLevelCommentsByParentId(articleId);
        List<Comment> comments = TreeUtil.listToTree(levelCommentsList, articleId);
        articleDTO.setComments(comments);
        List<Thumber> thumbers = ThumberUtil.allThumbers(articleId);
        articleDTO.setThumbers(thumbers);
        return articleDTO;
    }

    @PostMapping("/test")
    public List<Comment> tree(String apiRootId) {
        //找到根评论
        List<Comment> levelCommentsList = commentService.findNextLevelCommentsByParentId(apiRootId);
        List<Comment> comments = TreeUtil.listToTree(levelCommentsList, apiRootId);
        return comments;
    }
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
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

    }
