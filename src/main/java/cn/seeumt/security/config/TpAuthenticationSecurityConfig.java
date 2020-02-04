package cn.seeumt.security.config;

import cn.seeumt.security.filter.TpAuthenticationFilter;
import cn.seeumt.security.provider.TpAuthenticationProvider;
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
 * @author Seeut
 * @version 1.0
 * @date 2020/2/5 1:36
 */
@Component("tpAuthenticationSecurityConfig")
public class TpAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationSuccessHandler codeSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler codeFailureHandler;
    @Autowired
    @Qualifier("userDetailServiceImpl")
    private MyUserDetailService userDetailService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        TpAuthenticationFilter tpAuthenticationFilter = new TpAuthenticationFilter();
        tpAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        tpAuthenticationFilter.setAuthenticationSuccessHandler(codeSuccessHandler);
        tpAuthenticationFilter.setAuthenticationFailureHandler(codeFailureHandler);

        TpAuthenticationProvider tpAuthenticationProvider = new TpAuthenticationProvider();
        tpAuthenticationProvider.setUserDetailsService(userDetailService);


        http.authenticationProvider(tpAuthenticationProvider)
                .addFilterBefore(tpAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
