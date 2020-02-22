package cn.seeumt.security.config;

import cn.seeumt.security.filter.ValidateCodeFilter;
import cn.seeumt.security.filter.OtpAuthenticationFilter;
import cn.seeumt.security.provider.OtpAuthenticationProvider;
import cn.seeumt.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 19:54
 */
@Component("otpAuthenticationSecurityConfig")
public class OtpAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler codeSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler codeFailureHandler;
    @Autowired
    @Qualifier("userDetailServiceImpl")
    private MyUserDetailService userDetailService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        OtpAuthenticationFilter otpAuthenticationFilter = new OtpAuthenticationFilter();
        otpAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        otpAuthenticationFilter.setAuthenticationSuccessHandler(codeSuccessHandler);
        otpAuthenticationFilter.setAuthenticationFailureHandler(codeFailureHandler);

        OtpAuthenticationProvider otpAuthenticationProvider = new OtpAuthenticationProvider();
        otpAuthenticationProvider.setUserDetailsService(userDetailService);

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(codeFailureHandler);

        http.authenticationProvider(otpAuthenticationProvider)
                .addFilterBefore(otpAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
