package cn.seeumt.service;

import cn.seeumt.dataobject.Role;
import cn.seeumt.dataobject.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-03
 */
public interface UserRoleService {
    /**
     * 通过用户id选择其所对应的角色id
     * @param userId 用户id
     * @return List<Integer>
     */
    List<Integer> selectRoleIdsByUserId(String userId);

}
