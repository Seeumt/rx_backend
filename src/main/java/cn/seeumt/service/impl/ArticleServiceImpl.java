package cn.seeumt.service.impl;

import cn.seeumt.dataobject.Article;
import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.ArticleService;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public PageInfo<Article> queryAll(int num, int size,String keywords) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        PageHelper.startPage(num, size);
        if (keywords.length() == 0) {
            wrapper.orderByDesc("create_time");
            List<Article> articles = articleMapper.selectList(wrapper);
            return new PageInfo<>(articles);
        }
            wrapper.orderByDesc("create_time").like("md_content", keywords);
            List<Article> articles = articleMapper.selectList(wrapper);
            return new PageInfo<>(articles);
     }

    @Override
    public ResultVO insert(cn.seeumt.form.Article article) {
        Article article1 = new Article();
        BeanUtils.copyProperties(article, article1);
        article1.setCreateTime(new Date());
        article1.setUpdateTime(new Date());
        article1.setEnabled(true);
        article1.setDeleted(false);
        int insert = articleMapper.insert(article1);
        if (insert < 1) {
            throw new TipsException(TipsFlash.ARTICLE_INSERT_FAILED);
        }
        return ResultVO.success(Tips.SEND_SUCCESS);
    }


}
