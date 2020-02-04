package cn.seeumt.service;

import cn.seeumt.dataobject.Role;
import cn.seeumt.dataobject.User;
import cn.seeumt.model.UserDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-03
 */
public interface UserService{

    UserDetail selectUserDetailByUserId(String userId);

    UserDetail selectUserDetailByTelephone(String telephone);

    UserDetail selectUserDetailByOpenId(String openId);

    void resetPwd(String telephone,String password);


}
