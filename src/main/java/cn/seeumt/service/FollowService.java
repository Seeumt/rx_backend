package cn.seeumt.service;

import cn.seeumt.dataobject.Follow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-10
 */
public interface FollowService extends IService<Follow> {
    List<Follow> getAllLiker(String userId);
}
