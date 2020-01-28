//package cn.seeumt.service.impl;
//
//
//import cn.seeumt.dao.AuthMapper;
//import cn.seeumt.model.Role;
//import cn.seeumt.model.UserDetail;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
///**
// * 登陆身份认证
//
// */
//@Component(value="CustomUserDetailsService")
//public class CustomUserDetailsServiceImpl implements UserDetailsService {
//    private final AuthMapper authMapper;
//
//    public CustomUserDetailsServiceImpl(AuthMapper authMapper) {
//        this.authMapper = authMapper;
//    }
//
//    @Override
//    public UserDetail loadUserByUsername(String userId) throws UsernameNotFoundException {
//        UserDetail userDetail = authMapper.findByUsername(userId);
//        if (userDetail == null) {
//            throw new UsernameNotFoundException(String.format("No userDetail found with username '%s'.", name));
//        }
//        Role role = authMapper.findRoleByUserId(userDetail.getId());
//        userDetail.setRole(role);
//        return userDetail;
//    }
//}
