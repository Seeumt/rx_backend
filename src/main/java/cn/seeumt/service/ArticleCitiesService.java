package cn.seeumt.service;

import java.util.List;

/**
 * @author Seeumt
 * @since 2019-12-21
 */
public interface ArticleCitiesService {
    List<String> findCityIdsByArticleId(String articleId);
}
