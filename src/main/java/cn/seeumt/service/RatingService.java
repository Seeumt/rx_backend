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

    ResultVO addOrCancelRating(String apiRootId, String userId, Integer rating);

    Rating selectByApiRootIdAndUserId(String apiRootId, String userId);
}
