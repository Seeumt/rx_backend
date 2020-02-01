package cn.seeumt.service.impl;


import cn.seeumt.dao.AuthMapper;
import cn.seeumt.exception.TipsException;
import cn.seeumt.model.ResponseTokenUser;
import cn.seeumt.model.UserDetail;
import cn.seeumt.service.AuthService;
import cn.seeumt.utils.JwtTokenUtils;
import cn.seeumt.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

/**
 * @author: JoeTao
 * createAt: 2018/9/17
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userDetailServiceImpl")
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthMapper authMapper;



//    @Override
//    public UserDetail register(UserDetail userDetail) {
//        final String username = userDetail.getUsername();
//        if(authMapper.findByUsername(username)!=null) {
//            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户已存在"));
//        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        final String rawPassword = userDetail.getPassword();
//        userDetail.setPassword(encoder.encode(rawPassword));
//        userDetail.setLastPasswordResetDate(new Date());
//        authMapper.insert(userDetail);
//        long roleId = userDetail.getRole().getId();
//        Role role = authMapper.findRoleById(roleId);
//        userDetail.setRole(role);
//        authMapper.insertRole(userDetail.getId(), roleId);
//        return userDetail;
//    }

    @Override
    public ResponseTokenUser login(String username, String password) {
        //用户验证
        Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        String roles = StringUtils.join(authorities.toArray(), ",");
        String token = JwtTokenUtils.createToken(userDetail.getUsername(), roles,false);
        return new ResponseTokenUser(token, userDetail);

    }

//    @Override
//    public void logout(String token) {
//        token = token.substring(tokenHead.length());
//        String userName = jwtTokenUtil.getUsernameFromToken(token);
//        jwtTokenUtil.deleteToken(userName);
//    }


//    @Override
//    public ResponseTokenUser refresh(String oldToken) {
//        String token = oldToken.substring(tokenHead.length());
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(username);
//        if (jwtTokenUtil.canTokenBeRefreshed(token, userDetail.getLastPasswordResetDate())){
//            token =  jwtTokenUtil.refreshToken(token);
//            return new ResponseTokenUser(token, userDetail);
//        }
//        return null;
//    }

//    @Override
//    public UserDetail getUserByToken(String token) {
//
//        return JwtTokenUtils.ge(token);
//    }

    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new TipsException(1,e.getMessage());
        }
    }
}
