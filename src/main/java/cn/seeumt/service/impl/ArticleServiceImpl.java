package cn.seeumt.service.impl;

import cn.seeumt.dataobject.Article;
import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public List<Article> query(String userId) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return articleMapper.selectList(wrapper);
    }
}
