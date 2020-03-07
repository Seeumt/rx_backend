package cn.seeumt.service.impl;

import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.Article;
import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.dataobject.User;
import cn.seeumt.dto.ImgDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.model.MyPageHelper;
import cn.seeumt.service.*;
import cn.seeumt.vo.ArticleVO;
import cn.seeumt.vo.PostVO;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OssService ossService;
    @Autowired
    private FollowService followService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LoveService loveService;
    @Autowired
    private TagService tagServie;
    @Autowired
    private MediaTagsService mediaTagsService;
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
        Date date = new Date();
        article1.setCreateTime(date);
        article1.setUpdateTime(date);
        article1.setEnabled(true);
        article1.setDeleted(false);
        int insert = articleMapper.insert(article1);
        if (insert < 1) {
            throw new TipsException(TipsFlash.ARTICLE_INSERT_FAILED);
        }
        return ResultVO.success(Tips.SEND_SUCCESS);
    }

    @Override
    public ResultVO selectById(String articleId) {
        Article article = articleMapper.selectById(articleId);
        if (article == null) {
            throw new TipsException(TipsFlash.QUERY_ARTICLE_FAILED);
        }
        return ResultVO.success(assembleArticleVO(article));
    }

    @Override
    public ResultVO search(String keywords, int currentNum, int size) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if (keywords.length() <= 0) {
            wrapper.orderByDesc("create_time");
        }else {
            wrapper.orderByDesc("create_time").like("title", "%"+keywords+"%");
        }
        PageHelper.startPage(currentNum, size);
        List<Article> articles = articleMapper.selectList(wrapper);
        PageInfo<Article> articlePageInfo = new PageInfo<>(articles);
        List<ArticleVO> articleVos = assembleArticleVOList(articles);
        MyPageHelper<ArticleVO> myPageHelper = new MyPageHelper<>();
        BeanUtils.copyProperties(articlePageInfo, myPageHelper);
        myPageHelper.setList(articleVos);
        return ResultVO.success(myPageHelper);
    }

    public List<ArticleVO> assembleArticleVOList(List<Article> articles) {
        return  articles.stream().map(article -> {
            return assembleArticleVO(article);
        }).collect(Collectors.toList());

    }

    public ArticleVO assembleArticleVO(Article article) {
        ArticleVO articleVO = new ArticleVO();
        User user = userMapper.selectById(article.getUserId());
        if (user == null) {
            return null;
        }
        articleVO.setUsername(user.getUsername());
        articleVO.setNickname(user.getNickname());
        articleVO.setFaceIcon(user.getFaceIcon());
        BeanUtils.copyProperties(article, articleVO);
        articleVO.setThumbCount(commentService.selectCommentCountByRootIdAndType(article.getArticleId(), (byte) 3).size());
        if (article.getCoverPicture() == null) {
            ImgDTO imgDTO = ossService.queryByParentId(article.getArticleId());
            if (imgDTO.getUrls().length == 0) {
                articleVO.setCoverPicture("http://seeumt.oss-cn-hangzhou.aliyuncs.com/5ebfed05dbd340a69cd288d75628986a.jpg");
            }
            else {
                String[] urls = imgDTO.getUrls();
                articleVO.setCoverPicture(urls[0]);
            }
        }
        return articleVO;

    }

}
