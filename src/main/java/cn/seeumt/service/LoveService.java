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
    Boolean addOrCancelLove(String apiRootId,String userId);

    /**
     * 查询所有点赞者
     * @param apiRootId  articleId postId commentId
     * @return 点赞者的集合
     */
    List<Love> selectByApiRootId(String apiRootId);

    Boolean isLoveExist(String apiRootId,String userId);

    /**
     * 通过 apiRootId userId 查找该用户是否已点赞
     * @param apiRootId 点赞rootId
     * @param userId 用户id
     * @return 一条love记录
     */
    Love selectByApiRootIdAndUserId(String apiRootId, String userId);




}
