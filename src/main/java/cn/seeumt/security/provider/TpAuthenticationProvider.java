package cn.seeumt.security.provider;

import cn.seeumt.model.UserDetail;
import cn.seeumt.security.token.TpAuthenticationToken;
import cn.seeumt.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/5 1:15
 */
public class TpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("userDetailServiceImpl")
    private MyUserDetailService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetail userDetail = userDetailsService.findUserByTelephone((String) authentication.getPrincipal());
        String presentedPassword =  authentication.getCredentials().toString();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(presentedPassword, userDetail.getPassword())) {
            throw new BadCredentialsException("亲，您的密码不对哦！!");
        }
        TpAuthenticationToken tpAuthenticationToken = new TpAuthenticationToken(userDetail, "", userDetail.getAuthorities());
        tpAuthenticationToken.setDetails(authentication.getDetails());
        return tpAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TpAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public MyUserDetailService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(MyUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


}
