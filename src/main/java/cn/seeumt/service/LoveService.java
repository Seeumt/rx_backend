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

    /**
     * 是否已点赞（*）
     * @param apiRootId 根id
     * @param userId 用户id
     * @return
     */
    Boolean isLoveExist(String apiRootId,String userId);

    /**
     * 通过 apiRootId userId 查找该用户是否已点赞
     * @param apiRootId 点赞rootId
     * @param userId 用户id
     * @param type 类型
     * @return 一条love记录
     */
    Love selectByApiRootIdAndUserIdAndType(String apiRootId, String userId,Byte type);

    /**
     * 查询点赞数量
     * @param rootId 某一id
     * @param type 类型
     * @return List<Love>
     */
    List<Love> selectThumbCountByRootIdAndType(String rootId, Byte type);

    /**
     * 查询点踩数量
     * @param rootId 根id
     * @param type
     * @return
     */
    List<Love> selectHateCountByRootIdAndType(String rootId, Byte type);

    /**
     * 点赞点踩状态切换
     * @param apiRootId 根id
     * @param userId 用户id
     * @param type 类型
     * @return ResultVO
     */
    ResultVO changeLoveType(String apiRootId, String userId, Byte type);
}
