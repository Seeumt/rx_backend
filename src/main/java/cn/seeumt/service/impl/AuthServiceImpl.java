package cn.seeumt.service.impl;


import cn.seeumt.dao.AuthMapper;
import cn.seeumt.dao.WxUserMapper;
import cn.seeumt.exception.TipsException;
import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.model.UserDetail;
import cn.seeumt.security.token.MpAuthenticationToken;
import cn.seeumt.security.token.OtpAuthenticationToken;
import cn.seeumt.service.AuthService;
import cn.seeumt.utils.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private final JwtTokenUtils jwtTokenUtil;

    public AuthServiceImpl(JwtTokenUtils jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public UserDetail login(String username, String password) {
        //用户验证
        Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        String roles = StringUtils.join(authorities.toArray(), ",");
        String token = JwtTokenUtils.createToken(userDetail.getUsername(), roles, false);
        userDetail.setToken(token);
        return userDetail;

    }

    @Override
    public UserDetail OtpLogin(String telephone) {
       return getAuthenticationToken(new OtpAuthenticationToken(telephone));
    }

    @Override
    public UserDetail MpLogin(MPWXUserInfo mpwxUserInfo) {
        return getAuthenticationToken(new MpAuthenticationToken(mpwxUserInfo));
    }

    @Override
    public void logout(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        jwtTokenUtil.removeToken(token);
    }


    public UserDetail getAuthenticationToken(Authentication authenticationToken) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            //返回一个AuthenticationToken
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成token
            UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
            String roles = StringUtils.join(authorities.toArray(), ",");
            String token = JwtTokenUtils.createToken(userDetail.getOpenId(), roles, false);
            userDetail.setToken(token);
            return userDetail;
        } catch (DisabledException | BadCredentialsException | InternalAuthenticationServiceException e) {
            throw new TipsException(1,e.getMessage());
        }
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
        } catch (DisabledException | BadCredentialsException | InternalAuthenticationServiceException e) {
            throw new TipsException(1,e.getMessage());
        }
    }


}
