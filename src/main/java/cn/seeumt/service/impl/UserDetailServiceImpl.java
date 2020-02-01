package cn.seeumt.service.impl;
import cn.seeumt.dataobject.Role;
import com.google.common.collect.Lists;

import cn.seeumt.model.UserDetail;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/31 17:01
 */
@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 通过用户名查找实体类
     * @param s 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDetail userDetail = new UserDetail();
        userDetail.setUserId("67899");
        userDetail.setUsername("Tips");
        userDetail.setPassword(bCryptPasswordEncoder.encode("123456"));
        userDetail.setEnabled(true);
        userDetail.setLocked(false);
        List<Role> roles = Lists.newArrayList();
        roles.add(new Role(1,"ROLE_USER"));
//        roles.add(new Role(2,"ROLE_ADMIN"));
        userDetail.setRoles(roles);
        return userDetail;
    }
}
