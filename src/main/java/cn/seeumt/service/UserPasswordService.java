package cn.seeumt.service;

import cn.seeumt.dataobject.UserPassword;

/**
 * @author Seeumt
 * @date 2019/12/15 21:33
 */
public interface UserPasswordService {

    UserPassword selectByUserId(String userId);
}
