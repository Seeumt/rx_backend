package cn.seeumt.service;

import cn.seeumt.dataobject.UserPassword;
import cn.seeumt.vo.ResultVO;

/**
 * @author Seeumt
 * @date 2019/12/15 21:33
 */
public interface UserPasswordService {

    /**
     * 根据用户id查询密码
     * @param userId 用户id
     * @return password实体类
     */
    UserPassword selectByUserId(String userId);

    /**
     * 注册用户分离密码
     * @param userId 用户id
     * @param password 加密后的密码
     * @return int
     */
    int insert(String userId, String password);
}
