package cn.seeumt.service.impl;

import cn.seeumt.dao.UserInfoMapper;
import cn.seeumt.dao.UserPasswordMapper;
import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.dataobject.UserPassword;
import cn.seeumt.response.ResultVOUtil;
import cn.seeumt.service.UserInfoService;
import cn.seeumt.service.UserPasswordService;
import cn.seeumt.vo.ResultVO;
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
    @Autowired
    private UserPasswordService userPasswordService;

    @Override
    public UserInfo selectByPrimaryKey(String userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public ResultVO logIn(String userId, String password) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (userInfo != null) {
            UserPassword userPassword = userPasswordService.selectByUserId(userInfo.getId());
            if (password.equals(userPassword.getPassword())) {
                return ResultVO.success(userInfo);
            }
            return ResultVO.error(466, "密码错误");
        }
        return ResultVO.error(467, "用户不存在");
    }

}
