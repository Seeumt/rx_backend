package cn.seeumt.service;


import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.vo.ResultVO;

/**
 * @author Seeumt
 * @date 2019/12/8 14:56
 */
public interface UserInfoService {

    UserInfo selectByPrimaryKey(String userId);

    ResultVO logIn(String userId, String password);

    ResultVO register(String userId, String password);



//    ResultVO registerOrLogin(String username, String password);

}
