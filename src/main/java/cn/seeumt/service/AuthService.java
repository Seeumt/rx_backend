package cn.seeumt.service;

import cn.seeumt.form.MpWxUserInfo;
import cn.seeumt.model.UserDetail;

/**
 * @author Seeumt
 */
public interface AuthService {


    /**
     * 用户密码登录校验
     * @param username 用户名
     * @param password 密码
     * @return UserDetail
     */
    UserDetail upLogin(String username, String password);

    /**
     * 手机验证码登录校验
     * @param telephone 手机号
     * @return UserDetail
     */
    UserDetail otpLogin(String telephone);

    /**
     * 微信授权登录校验
     * @param mpwxUserInfo 微信实体类
     * @return UserDetail
     */
    UserDetail mpLogin(MpWxUserInfo mpwxUserInfo);

    /**
     * 手机号密码登录
     * @param telephone
     * @param password
     * @return UserDetail
     */
    UserDetail tpLogin(String telephone, String password);

    /**
     * 登出
     * @param token
     */
    void logout(String token);



}
