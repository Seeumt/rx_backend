package cn.seeumt.service.impl;

import cn.seeumt.dao.UserPasswordMapper;
import cn.seeumt.dataobject.UserPassword;
import cn.seeumt.service.UserPasswordService;
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

}
