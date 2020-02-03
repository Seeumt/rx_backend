package cn.seeumt.security.provider;

import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.model.UserDetail;
import cn.seeumt.security.token.MpAuthenticationToken;
import cn.seeumt.service.MyUserDetailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/3 10:54
 */
@Data
public class MpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetail userDetail = userDetailsService.findUserByOpenId((MPWXUserInfo) authentication.getPrincipal());
        if (userDetail == null) {
            throw new InternalAuthenticationServiceException("系统异常");
        }
        MpAuthenticationToken mpAuthenticationToken = new MpAuthenticationToken(
                userDetail, userDetail.getAuthorities());
        mpAuthenticationToken.setDetails(authentication.getDetails());
        return mpAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MpAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
