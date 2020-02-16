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

    ResultVO addOrCancelCollect(String apiRootId, String userId);

    Collect selectByApiRootIdAndUserId(String apiRootId, String userId);
}
