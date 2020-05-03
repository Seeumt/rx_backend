package cn.seeumt.service;

import cn.seeumt.dataobject.Article;
import cn.seeumt.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpException;

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
    /**
     * 通过用户id查找所有文章
     * @param userId
     * @return
     */
    List<Article> query(String userId);

    /**
     * 分页 关键词 查询文章
     * @param num 当前页
     * @param size 每页条数
     * @param keywords 关键词
     * @return PageInfo<Article>
     */
    PageInfo<Article> queryAll(int num, int size,String keywords);

    /**
     * 发布文章
     * @param article 游记id
     * @return ResultVO
     * @throws HttpException 网络异常
     */
    ResultVO insert(cn.seeumt.form.Article article) throws HttpException;

    /**
     * 通过文章主键查询文章
     * @param articleId 文章主键
     * @return ResultVO
     */
    ResultVO selectById(String articleId);

    /**
     * 通过关键字搜索并分页
     * @param keywords 关键词
     * @param currentNum 当前页码
     * @param size 每页数量
     * @return ResultVO
     */
    ResultVO search(String keywords, int currentNum, int size);
}
