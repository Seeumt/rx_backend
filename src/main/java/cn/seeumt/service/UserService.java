package cn.seeumt.service;

import cn.seeumt.dataobject.User;
import cn.seeumt.model.UserDetail;
import cn.seeumt.vo.ResultVO;

/**
 * @author Seeumt
 * @since 2020-02-03
 */
public interface UserService{

    /**
     * 通过查询用户id详细信息
     * @param userId 用户id
     * @return UserDetail
     */
    UserDetail selectUserDetailByUserId(String userId);

    /**
     * 通过查询用户详细信息
     * @param username 用户名
     * @return UserDetail
     */
    UserDetail selectUserDetailByUsername(String username);

    /**
     * 通过手机号查询用户详细信息
     * @param telephone 用户手机号
     * @return UserDetail
     */
    UserDetail selectUserDetailByTelephone(String telephone);

    /**
     * 通过openId查询用户详细信息
     * @param openId 用户openId
     * @return UserDetail
     */
    UserDetail selectUserDetailByOpenId(String openId);

    /**
     * 将验证码加入redis
     * @param telephone 手机号
     * @param otpCode 验证码
     */
    void addCache(String telephone, String otpCode);

    /**
     * 校验验证码是否合法
     * @param telephone 手机号
     * @param otpCode 验证码
     * @return ResultVO
     */
    ResultVO validCode(String telephone, String otpCode);

    /**
     * 重置密码
     * @param telephone 手机号
     * @param password 密码
     * @return ResultVO
     */
    boolean resetPwd(String telephone, String password);

    /**
     * wx用户更新头像
     * @param userId 用户id
     * @param originUrl 原始url
     * @return ResultVO
     */
    ResultVO uploadFaceIcon(String userId, String originUrl);

    /**
     * 根据手机号查找用户
     * @param telephone
     * @return User
     */
    User selectByTelephone(String telephone);

    /**
     * 在线人数统计
     * @param gap 时间间隔（毫秒）
     * @return ResultVO
     */
    ResultVO onlineUser(Long gap);


    /**
     * 微信用户绑定手机号
     * @param openId openId
     * @param telephone 手机号
     * @return int
     */
    int bindTel(String openId, String telephone);
}
