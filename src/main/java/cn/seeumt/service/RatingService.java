package cn.seeumt.service;

import cn.seeumt.dataobject.Collect;
import cn.seeumt.dataobject.Rating;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-17
 */
public interface RatingService extends IService<Rating> {

    /**
     * 评分或取消评分
     * @param apiRootId id(articleId,postId)
     * @param userId 用户id
     * @param rating 星级
     * @return ResultVO
     */
    ResultVO addOrCancelRating(String apiRootId, String userId, Integer rating);

    /**
     * 根据根id及用户id查询评分
     * @param apiRootId 根id
     * @param userId 用户id
     * @return Rating
     */
    Rating selectByApiRootIdAndUserId(String apiRootId, String userId);
}
