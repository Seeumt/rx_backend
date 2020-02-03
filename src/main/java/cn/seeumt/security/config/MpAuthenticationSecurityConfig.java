package cn.seeumt.security.config;

import cn.seeumt.security.filter.MpAuthenticationFilter;
import cn.seeumt.security.filter.ValidateCodeFilter;
import cn.seeumt.security.provider.MpAuthenticationProvider;
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
 * @date 2020/2/3 12:01
 */
@Component("mpAuthenticationSecurityConfig")
public class MpAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler codeSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler codeFailureHandler;
    @Autowired
    @Qualifier("userDetailServiceImpl")
    private MyUserDetailService userDetailService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MpAuthenticationFilter mpAuthenticationFilter = new MpAuthenticationFilter();
        mpAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        mpAuthenticationFilter.setAuthenticationSuccessHandler(codeSuccessHandler);
        mpAuthenticationFilter.setAuthenticationFailureHandler(codeFailureHandler);

        MpAuthenticationProvider mpAuthenticationProvider = new MpAuthenticationProvider();
        mpAuthenticationProvider.setUserDetailsService(userDetailService);


        http.authenticationProvider(mpAuthenticationProvider)
                .addFilterBefore(mpAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

