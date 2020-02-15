package cn.seeumt.service.impl;

import cn.seeumt.dao.RoleMapper;
import cn.seeumt.dao.WxUserMapper;
import cn.seeumt.dataobject.*;
import cn.seeumt.dao.UserMapper;
import cn.seeumt.dto.MPWXUserInfoDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.exception.TipsException;
import cn.seeumt.model.UserDetail;
import cn.seeumt.service.UserRoleService;
import cn.seeumt.service.UserService;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-03
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetail selectUserDetailByUserId(String userId) {
        User user = userMapper.selectById(userId);
        return createUserDetail(user);
    }

    @Override
    public UserDetail selectUserDetailByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        return createUserDetail(user);
    }

    @Override
    public UserDetail selectUserDetailByTelephone(String telephone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", telephone);
        User user = userMapper.selectOne(queryWrapper);
        return createUserDetail(user);
    }



    @Override
    public UserDetail selectUserDetailByOpenId(String openId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", openId);
        User user = userMapper.selectOne(queryWrapper);
        return createUserDetail(user);
    }

    @Override
    @Transactional
    public int resetPwd(String telephone,String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", telephone);
        User user = userMapper.selectOne(queryWrapper);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userMapper.updateById(user);
    }


    public UserDetail createUserDetail(User user) {
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(user, userDetail);
        List<Integer> roleIds= userRoleService.selectRoleIdsByUserId(user.getUserId());
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        userDetail.setRoles(roles);
        return userDetail;
    }

    @Override
    public ResultVO uploadFaceIcon(String userId, String originUrl) {
        QueryWrapper<WxUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        WxUser wxUser = wxUserMapper.selectOne(wrapper);
//        String dbUrl = AliyunOssUtil.getDBUrl(originUrl);
//        wxUser.setAvatarUrl(dbUrl);
        wxUser.setAvatarUrl(originUrl);
        wxUserMapper.updateById(wxUser);
        MPWXUserInfoDTO mpwxUserInfoDTO = new MPWXUserInfoDTO();
        BeanUtils.copyProperties(wxUser,mpwxUserInfoDTO);
//        mpwxUserInfoDTO.setAvatarUrl(originUrl);
        return ResultVO.success(mpwxUserInfoDTO,"更新头像成功");
    }

    @Override
    public User selectByTelephone(String telephone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", telephone);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public int bindTel(String openId, String telephone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", openId);
        QueryWrapper<WxUser> wxWrapper = new QueryWrapper<>();
        wxWrapper.eq("open_id", openId);
        WxUser wxUser = wxUserMapper.selectOne(wxWrapper);
        if (wxUser != null) {
            wxUser.setTelephone(telephone);
            int a = wxUserMapper.updateById(wxUser);
            if (a < 0) {
                throw new TipsException(200007, "绑定手机异常2");
            }
        }else {
            throw new TipsException(200004, "无此微信用户");
        }
        User user = userMapper.selectOne(wrapper);
        if (user != null) {
            user.setTelephone(telephone);
           return userMapper.updateById(user);
        }else {
            throw new TipsException(200007, "绑定手机异常1");
        }
    }


}
