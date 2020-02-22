package cn.seeumt.service;

import cn.seeumt.form.MpWxUserInfo;
import cn.seeumt.model.UserDetail;

/**
 * @author: JoeTao
 * createAt: 2018/9/17
 */
public interface AuthService {
    /**
     * 注册用户
     * @param userDetail
     * @return
     */
//    UserDetail register(UserDetail userDetail);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    UserDetail upLogin(String username, String password);

    UserDetail otpLogin(String telephone);

    UserDetail mpLogin(MpWxUserInfo mpwxUserInfo);

    UserDetail tpLogin(String telephone, String password);

    void logout(String token);


    /**
     * 登出
     * @param token
     */
//    void logout(String token);

    /**
     * 刷新Token
     * @param oldToken
     * @return
     */
//    ResponseTokenUser refresh(String oldToken);

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
//    UserDetail getUserByToken(String token);
}
