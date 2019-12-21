package cn.seeumt.controller;


import cn.seeumt.dataobject.City;
import cn.seeumt.dataobject.Tag;
import cn.seeumt.dto.ArticleDTO;

import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import cn.seeumt.service.*;
import cn.seeumt.utils.ThumberUtil;

import cn.seeumt.utils.TreeUtil;
import cn.seeumt.vo.CityVO;
import cn.seeumt.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Seeumt
 * @since 2019-12-21
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleTagsService articleTagsService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleCitiesService articleCitiesService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/findArticle")
    public ArticleDTO findAnArticle(String articleId) {
        ArticleDTO articleDTO = new ArticleDTO();
        List<String> tagIds = articleTagsService.findTagIdsByArticleId(articleId);
        List<TagVO> tagVOS = tagService.findByTagIds(tagIds);
        articleDTO.setTagVOS(tagVOS);
        List<String> cityIds = articleCitiesService.findCityIdsByArticleId(articleId);
        List<CityVO> cityVOS = cityService.findByCityIds(cityIds);
        articleDTO.setViaCitiesVOS(cityVOS);
        List<Comment> levelCommentsList = commentService.findNextLevelCommentsByParentId(articleId);
        List<Comment> comments = TreeUtil.listToTree(levelCommentsList, articleId);
        articleDTO.setComments(comments);
        List<Thumber> thumbers = ThumberUtil.allThumbers(articleId);
        articleDTO.setThumbers(thumbers);
        return articleDTO;
    }

    }
