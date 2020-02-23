package cn.seeumt.service;

import cn.seeumt.dataobject.Follow;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-10
 */
public interface FollowService extends IService<Follow> {
    /**
     * 查询所有关注的人
     * @param userId 用户id
     * @return List<Follow>
     */
    List<Follow> getAllIdol(String userId);

    /**
     * 添加关注
     * @param userId 用户id
     * @param idolId 用户id
     * @return 关注是否成功
     */
    ResultVO add(String userId, String idolId);

    /**
     * 是否已关注
     * @param idolId
     * @param userId
     * @return ResultVO
     */
    ResultVO isIdol(String idolId, String userId);
}
