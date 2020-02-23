package cn.seeumt.service.impl;

import cn.seeumt.dao.RoleMapper;
import cn.seeumt.dao.WxUserMapper;
import cn.seeumt.dataobject.*;
import cn.seeumt.dao.UserMapper;
import cn.seeumt.dto.MpWxUserInfoDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.model.UserDetail;
import cn.seeumt.service.RedisService;
import cn.seeumt.service.UserRoleService;
import cn.seeumt.service.UserService;
import cn.seeumt.utils.KeyUtil;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    private RedisService redisService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static final String REDIS_KEY_PREFIX_OTP_CODE = "otp";
    public static final Long OTP_CODE_EXPIRE_SECONDS = 300L;

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
    public void addCache(String telephone, String otpCode) {
        redisService.set(REDIS_KEY_PREFIX_OTP_CODE+telephone,otpCode);
        redisService.expire(REDIS_KEY_PREFIX_OTP_CODE + telephone, OTP_CODE_EXPIRE_SECONDS);
    }

    @Override
    public ResultVO validCode(String telephone, String otpCode) {
        String redisOtpCode= redisService.get(REDIS_KEY_PREFIX_OTP_CODE+telephone);
        if(StringUtils.isEmpty(redisOtpCode)){
            return ResultVO.error(Tips.OTP_CODE_EXPIRED.getCode(),Tips.OTP_CODE_EXPIRED.getMsg(),false);
        }
        if(!"".equals(redisOtpCode)&&!otpCode.equals(redisOtpCode)){
            return ResultVO.error(Tips.OTP_CODE_ERROR.getCode(),Tips.OTP_CODE_ERROR.getMsg(),false);
        }
        return  ResultVO.success(Tips.BIND_SUCCESS.getCode(),Tips.BIND_SUCCESS.getMsg(),true);
    }

    @Override
    @Transactional(rollbackFor = TipsException.class)
    public boolean resetPwd(String telephone, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", telephone);
        User user = userMapper.selectOne(queryWrapper);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        int i = userMapper.updateById(user);
        if (i < 1) {
            throw new TipsException(TipsFlash.UPDATE_USER_PASSWORD_EXCEPTION);
        }
        return true;
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
        wxUser.setAvatarUrl(originUrl);
        wxUserMapper.updateById(wxUser);
        MpWxUserInfoDTO mpwxUserInfoDTO = new MpWxUserInfoDTO();
        BeanUtils.copyProperties(wxUser,mpwxUserInfoDTO);
        return ResultVO.success(mpwxUserInfoDTO,"更新头像成功");
    }

    @Override
    public User selectByTelephone(String telephone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", telephone);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = TipsException.class)
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
                throw new TipsException(TipsFlash.UPDATE_WX_USER_EXCEPTION);
            }
        }else {
            throw new TipsException(TipsFlash.FIND_WX_USER_EXCEPTION);
        }
        User user = userMapper.selectOne(wrapper);
        boolean hasTel = true;
        if (user == null) {
            throw new TipsException(TipsFlash.FIND_USER_EXCEPTION);
        }else {
            if (Tips.DEFAULT_TEL.getMsg().equals(user.getTelephone())) {
                hasTel = false;
            }
            System.out.println(telephone);
            user.setTelephone(telephone);
            int i = userMapper.updateById(user);
            if (i < 1) {
                throw new TipsException(TipsFlash.UPDATE_USER_EXCEPTION);
            }
            if (!hasTel) {
                Long pwd = KeyUtil.genUniqueKey();
                user.setPassword(bCryptPasswordEncoder.encode(pwd.toString()));
                try {
                    System.out.println(pwd);
                } catch (Exception e) {
                    throw new TipsException(TipsFlash.SEND_WELCOME_MSG_EXCEPTION);
                }
                int m = userMapper.updateById(user);
                if (m < 1) {
                    throw new TipsException(TipsFlash.UPDATE_USER_EXCEPTION);
                }
            }
            return i;

        }
    }

    @Override
    public ResultVO onlineUser(Long gap) {
        Long nowTime = System.currentTimeMillis();
        Date nowDate = new Date(nowTime);
        Date validDate = new Date(nowTime-gap);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("last_visit_time", validDate, nowDate);
        Integer count = userMapper.selectCount(queryWrapper);
        return ResultVO.success(count);
    }



}
