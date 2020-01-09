package cn.seeumt.service.impl;
import java.util.Date;

import cn.seeumt.dao.UserInfoMapper;
import cn.seeumt.dao.UserPasswordMapper;
import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.dataobject.UserPassword;
import cn.seeumt.enums.Tips;
import cn.seeumt.response.ResultVOUtil;
import cn.seeumt.service.UserInfoService;
import cn.seeumt.service.UserPasswordService;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
                return ResultVO.success(userInfo,"登陆成功！");
            }
            return ResultVO.error(466, "用户名或密码错误");
        }

        return  register(userId, password);
    }

    @Override
    public ResultVO register(String userId, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setUsername(userId);
        userInfo.setNickname("hhh");
        userInfo.setFaceIcon("http://img1.imgtn.bdimg.com/it/u=2010748051,1812828348&fm=11&gp=0.jpg");
        userInfo.setTelephone(111);
        userInfo.setEmail(userId+"@gmail.com");
        userInfo.setStatus(true);
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());
        userInfo.setStu(false);
        int insert = userInfoMapper.insert(userInfo);
        if (insert > 0) {
            int insert1 = userPasswordService.insert(userInfo.getId(), password);
            if (insert1 > 0) {
                return ResultVO.success(userInfo,"注册成功！");
            }
            return ResultVO.error(Tips.INSERT_FAIL.getCode(), Tips.INSERT_FAIL.getMsg());
        }
        return ResultVO.error(Tips.INSERT_FAIL.getCode(), Tips.INSERT_FAIL.getMsg());
    }


}
