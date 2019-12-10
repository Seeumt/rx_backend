package cn.seeumt.service.impl;

import cn.seeumt.dao.UserInfoMapper;
import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.service.UserInfoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seeumt
 * @date 2019/12/8 14:57
 */
@Data
@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectByPrimaryKey(String userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }



}
