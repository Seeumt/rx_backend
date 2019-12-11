package cn.seeumt.service;

import cn.seeumt.dataobject.Love;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 12:25
 */
public interface LoveService {
    /**
     * 点赞功能 给article，post、comment点赞
     * @param apiRootId  articleId postId commentId
     * @param userId 点赞者的Id
     * @return int
     */
    int addLove(String apiRootId,String userId);

    /**
     * 查询所有点赞者
     * @param apiRootId  articleId postId commentId
     * @return 点赞者的集合
     */
    List<Love> selectByApiRootId(String apiRootId);

}
