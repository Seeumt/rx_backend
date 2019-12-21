package cn.seeumt.service;

import cn.seeumt.dataobject.ArticleTags;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
public interface ArticleTagsService{

    List<String> findTagIdsByArticleId(String articleId);
}
