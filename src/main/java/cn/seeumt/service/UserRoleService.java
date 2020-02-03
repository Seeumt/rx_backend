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
    List<Integer> selectRoleIdsByUserId(String userId);

}
