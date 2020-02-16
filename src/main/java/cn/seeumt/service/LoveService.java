package cn.seeumt.service;

import cn.seeumt.dataobject.Comment;
import cn.seeumt.dataobject.Love;
import cn.seeumt.vo.ResultVO;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 12:25
 */
public interface LoveService {
    /**
     * 点赞点踩功能 给article，post、comment点赞
     * @param apiRootId  articleId postId commentId
     * @param userId 点赞者的Id
     * @param type  点赞/点踩
     * @return T/F
     */
    ResultVO addOrCancelLove(String apiRootId, String userId, Integer type);

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
    Love selectByApiRootIdAndUserIdAndType(String apiRootId, String userId,Byte type);

    List<Love> selectThumbCountByRootIdAndType(String rootId, Byte type);

    List<Love> selectHateCountByRootIdAndType(String rootId, Byte type);


    ResultVO changeLoveType(String apiRootId, String userId, Byte type);
}
