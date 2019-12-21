package cn.seeumt.service.impl;

import cn.seeumt.dataobject.Article;
import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
