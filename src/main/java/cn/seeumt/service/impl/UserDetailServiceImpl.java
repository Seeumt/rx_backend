package cn.seeumt.service.impl;
import cn.seeumt.dao.UserMapper;
import cn.seeumt.dao.UserRoleMapper;
import cn.seeumt.dataobject.Role;
import cn.seeumt.dataobject.User;
import cn.seeumt.dataobject.UserRole;
import cn.seeumt.dataobject.WxUser;
import cn.seeumt.enums.Tips;
import cn.seeumt.exception.TipsException;
import cn.seeumt.form.MpWxUserInfo;
import cn.seeumt.service.MyUserDetailService;
import cn.seeumt.service.UserService;
import cn.seeumt.service.WxUserService;
import cn.seeumt.utils.KeyUtil;
import cn.seeumt.utils.UuidUtil;
import com.google.common.collect.Lists;

import cn.seeumt.model.UserDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/31 17:01
 */
@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements MyUserDetailService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private WxUserService wxUserService;
    /**
     * 通过用户名查找实体类
     *
     * @param s 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     */

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userService.selectUserDetailByUsername(s);
    }

    @Override
    public UserDetail findUserByTelephone(String telephone) {
        UserDetail userDetail = userService.selectUserDetailByTelephone(telephone);
        if (userDetail == null) {
            return null;
        }
        return userDetail;
    }

    @Override
    public UserDetail findUserByOpenId(MpWxUserInfo mpwxUserInfo) {
        String openId = mpwxUserInfo.getOpenId();
        String sessionKey = UuidUtil.getUuid();
        WxUser wxUser = wxUserService.selectByOpenId(openId);
        String skey = UuidUtil.getUuid();
        if (wxUser == null) {
            WxUser newWxUser = wxUserService.insert(mpwxUserInfo, openId, sessionKey, skey);
            User user = new User();
            String userId = UuidUtil.getUuid();
            user.setUserId(userId);
            user.setUsername("Tips_"+KeyUtil.genUniqueUsername());
            user.setNickname(mpwxUserInfo.getNickName());
            user.setPassword(KeyUtil.genUniqueKey().toString());
            user.setTelephone(Tips.DEFAULT_TEL.getMsg());
            user.setFaceIcon(mpwxUserInfo.getAvatarUrl());
            user.setEnabled(true);
            user.setLocked(false);
            user.setCreateTime(new Date());
            user.setLastVisitTime(new Date());
            user.setIsRememberMe(false);
            user.setOpenId(mpwxUserInfo.getOpenId());
            System.out.println(user);
            int insert = userMapper.insert(user);
            if (insert < 0) {
                throw new TipsException(123, "微信登录异常");
            }else {
                UserDetail userDetail = new UserDetail();
                BeanUtils.copyProperties(user, userDetail);
                Role role = new Role(2, "ROLE_USER");
                List<Role> roles = Lists.newArrayList();
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(2);
                userRoleMapper.insert(userRole);
                roles.add(role);
                userDetail.setRoles(roles);
                userDetail.setNickname(mpwxUserInfo.getNickName());
                return userDetail;
            }

        }else {
            wxUser.setLastVisitTime(new Date());
            wxUser.setSkey(skey);
            wxUserService.update(wxUser);
            return userService.selectUserDetailByOpenId(wxUser.getOpenId());

        }
    }
}
