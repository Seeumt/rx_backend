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

    UserDetail findUserByTelephone(String telephone);

    UserDetail findUserByOpenId(MpWxUserInfo mpwxUserInfo);

}
