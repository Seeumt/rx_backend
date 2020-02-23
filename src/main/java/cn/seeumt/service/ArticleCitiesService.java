package cn.seeumt.service;

import java.util.List;

/**
 * @author Seeumt
 * @since 2019-12-21
 */
public interface ArticleCitiesService {
    /**
     * 通过文章id选择城市的id集合
     * @param articleId 文章id
     * @return List<String>
     */
    List<String> findCityIdsByArticleId(String articleId);
}
