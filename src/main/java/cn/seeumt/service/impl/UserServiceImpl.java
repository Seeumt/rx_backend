package cn.seeumt.service.impl;

import cn.seeumt.dao.RoleMapper;
import cn.seeumt.dataobject.Role;
import cn.seeumt.dataobject.User;
import cn.seeumt.dao.UserMapper;
import cn.seeumt.model.UserDetail;
import cn.seeumt.service.RoleService;
import cn.seeumt.service.UserRoleService;
import cn.seeumt.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public UserDetail selectUserDetailByUserId(String userId) {
        User user = userMapper.selectById(userId);
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


    public UserDetail createUserDetail(User user) {
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(user, userDetail);
        List<Integer> roleIds= userRoleService.selectRoleIdsByUserId(user.getUserId());
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        userDetail.setRoles(roles);
        return userDetail;
    }
}
