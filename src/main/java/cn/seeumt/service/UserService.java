package cn.seeumt.service;

import cn.seeumt.dataobject.User;
import cn.seeumt.model.UserDetail;
import cn.seeumt.vo.ResultVO;

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

    UserDetail selectUserDetailByUsername(String username);

    UserDetail selectUserDetailByTelephone(String telephone);

    UserDetail selectUserDetailByOpenId(String openId);

    void addCache(String telephone, String otpCode);

    ResultVO validCode(String telephone, String otpCode);

    boolean resetPwd(String telephone, String password);

    // TODO: 2020/2/4 wx用户更新头像
    ResultVO uploadFaceIcon(String userId, String originUrl);

    User selectByTelephone(String telephone);

    ResultVO onlineUser(Long gap);

    Boolean validTel(String telephone);
    int bindTel(String openId, String telephone);
}
