package cn.seeumt.service;


import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.vo.ResultVO;
import cn.seeumt.vo.UserVO;

/**
 * @author Seeumt
 * @date 2019/12/8 14:56
 */
public interface UserInfoService {

    UserInfo selectByPrimaryKey(String userId);

    ResultVO logIn(String userId, String password);

    ResultVO register(String userId, String password);

    ResultVO uploadFaceIcon(String userId, String originUrl);

    UserVO selectByUserId(String userId);



//    ResultVO registerOrLogin(String username, String password);

}
