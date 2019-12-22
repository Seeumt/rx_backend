package cn.seeumt.service.impl;

import cn.seeumt.dataobject.ArticleTags;
import cn.seeumt.dao.ArticleTagsMapper;
import cn.seeumt.service.ArticleTagsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Seeumt
 * @since 2019-12-21
 */
@Service
public class ArticleTagsServiceImpl implements ArticleTagsService {
    @Autowired
    private ArticleTagsMapper articleTagsMapper;

    @Override
    public List<String> findTagIdsByArticleId(String articleId) {
        QueryWrapper<ArticleTags> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        List<ArticleTags> articleTags = articleTagsMapper.selectList(queryWrapper);
//        List<String> tagIds = articleTags.stream().sorted((at1,at2)->Integer.parseInt(at1.getTagId())-Integer.parseInt(at2.getTagId())).map(articleTag -> articleTag.getTagId()).collect(Collectors.toList());
        List<String> tagIds = articleTags.stream().sorted(Comparator.comparing(ArticleTags::getTagId)).map(articleTag -> articleTag.getTagId()).collect(Collectors.toList());
        return tagIds;
    }



}
