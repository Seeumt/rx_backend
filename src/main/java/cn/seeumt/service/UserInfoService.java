package cn.seeumt.service;


import cn.seeumt.dataobject.UserInfo;

/**
 * @author Seeumt
 * @date 2019/12/8 14:56
 */
public interface UserInfoService {

    UserInfo selectByPrimaryKey(String userId);

}
