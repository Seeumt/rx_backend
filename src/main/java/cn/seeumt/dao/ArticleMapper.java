package cn.seeumt.dao;

import cn.seeumt.dataobject.Article;
import cn.seeumt.model.Thumber;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Article article);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    List<Article> selectByCommentId(String commentId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);


}
