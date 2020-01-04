package cn.seeumt.service;

import cn.seeumt.dataobject.Article;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
public interface ArticleService{
    List<Article> query(String userId);
    PageInfo<Article> queryAll(int num, int size,String keywords);
}
