package cn.seeumt.service;

import cn.seeumt.form.MpWxUserInfo;
import cn.seeumt.model.UserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 17:13
 */
public interface MyUserDetailService extends UserDetailsService {

    /**
     * 通过手机号查找用户权限等
     * @param telephone 手机号
     * @return UserDetail
     */
    UserDetail findUserByTelephone(String telephone);

    /**
     * 通过openId查找用户权限等
     * @param mpwxUserInfo 微信原生实体
     * @return UserDetail
     */
    UserDetail findUserByOpenId(MpWxUserInfo mpwxUserInfo);

}
