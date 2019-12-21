package cn.seeumt.controller;


import cn.seeumt.dao.ArticleTagsMapper;
import cn.seeumt.dataobject.City;
import cn.seeumt.dataobject.Tag;
import cn.seeumt.dto.ArticleDTO;
import cn.seeumt.dto.PostDTO;
import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import cn.seeumt.service.*;
import cn.seeumt.utils.ThumberUtil;
import cn.seeumt.utils.TreeUtil;
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
    private ArticleService articleService;
    @Autowired
    private ArticleTagsService articleTagsService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleCitiesService articleCitiesService;
    @Autowired
    private CityService cityService;

    @GetMapping("/findArticle")
    public ArticleDTO findAnArticle(String articleId) {
        ArticleDTO articleDTO = new ArticleDTO();
        List<String> tagIds = articleTagsService.findTagIdsByArticleId(articleId);
        List<Tag> tags = tagService.findByTagIds(tagIds);
        articleDTO.setTags(tags);
        List<String> cityIds = articleCitiesService.findCityIdsByArticleId(articleId);
        List<City> citys = cityService.findByCityIds(cityIds);
        articleDTO.setViaCities(citys);
        List<Thumber> thumbers = ThumberUtil.allThumbers(articleId);
        articleDTO.setThumbers(thumbers);
        return articleDTO;
    }

    }
