package cn.seeumt.service;

import cn.seeumt.dataobject.Collect;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-16
 */
public interface CollectService extends IService<Collect> {

    /**
     * 添加/取消收藏
     * @param apiRootId id(文章id,动态id,纪念品id)
     * @param userId 用户id
     * @return ResultVO
     */
    ResultVO addOrCancelCollect(String apiRootId, String userId);

    /**
     * 查询收藏信息
     * @param apiRootId id(文章id,动态id,纪念品id)
     * @param userId 用户id
     * @return Collect
     */
    Collect selectByApiRootIdAndUserId(String apiRootId, String userId);
}
