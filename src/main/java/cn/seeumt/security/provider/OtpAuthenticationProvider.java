package cn.seeumt.security.provider;

import cn.seeumt.model.UserDetail;
import cn.seeumt.security.token.OtpAuthenticationToken;
import cn.seeumt.service.MyUserDetailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 18:23
 */
// TODO: 2020/2/2 333
@Data
public class OtpAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetail userDetail = userDetailsService.findUserByTelephone((String) authentication.getPrincipal());
        if (userDetail == null) {
            throw new InternalAuthenticationServiceException("无此用户");
        }
        OtpAuthenticationToken otpAuthenticationToken = new OtpAuthenticationToken(
                userDetail, userDetail.getAuthorities());
        otpAuthenticationToken.setDetails(authentication.getDetails());
        return otpAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
