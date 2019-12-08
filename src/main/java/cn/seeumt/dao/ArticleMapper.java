package cn.seeumt.dao;

import cn.seeumt.dataobject.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Article article);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
}
