package cn.seeumt.service;

import cn.seeumt.dataobject.Article;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 12:05
 */
public interface ArticleService {

    Article createArticle(Article article);

    Article selectByPrimaryKey(String articleId);


}
