package cn.seeumt.service.impl;

import cn.seeumt.dao.UserPasswordMapper;
import cn.seeumt.dataobject.UserPassword;
import cn.seeumt.enums.Tips;
import cn.seeumt.service.UserPasswordService;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seeumt
 * @date 2019/12/15 21:34
 */
@Service
public class UserPasswordServiceImpl implements UserPasswordService {

    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Override
    public UserPassword selectByUserId(String userId) {
        return userPasswordMapper.selectByUserId(userId);
    }

    @Override
    public int insert(String userId, String password) {
        UserPassword userPassword = new UserPassword();
        userPassword.setId(UuidUtil.getUUID());
        userPassword.setPassword(password);
        userPassword.setUserId(userId);
        int insert = userPasswordMapper.insert(userPassword);
        if (insert > 0) {
            return insert;
        }
        return 0;

    }

}
