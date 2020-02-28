package cn.seeumt.security;

import cn.seeumt.security.config.MpAuthenticationSecurityConfig;
import cn.seeumt.security.config.TpAuthenticationSecurityConfig;
import cn.seeumt.security.filter.ValidateCodeFilter;
import cn.seeumt.security.config.OtpAuthenticationSecurityConfig;
import cn.seeumt.security.jwt.JwtAccessDeniedHandler;
import cn.seeumt.security.jwt.JwtAuthenticationEntryPoint;
import cn.seeumt.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/31 15:06
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler codeSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler codeFailureHandler;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private OtpAuthenticationSecurityConfig otpAuthenticationSecurityConfig;
    @Autowired
    private MpAuthenticationSecurityConfig mpAuthenticationSecurityConfig;
    @Autowired
    private TpAuthenticationSecurityConfig tpAuthenticationSecurityConfig;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(codeFailureHandler);
        http.authorizeRequests()
                // 测试用资源，需要验证了的用户才能访问
                .antMatchers("/loves/**").authenticated()
                .antMatchers("/follows/**").authenticated()
                .antMatchers(HttpMethod.POST,"/comments/**").authenticated()
                .antMatchers("/follows/**").hasAuthority("ROLE_USER")
                .antMatchers("/users/**").permitAll()
                .antMatchers(HttpMethod.POST).authenticated()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers("http://localhost:8086/swagger-ui.html").permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService)
                .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                //添加无权限时的处理
                .accessDeniedHandler(new JwtAccessDeniedHandler()).
                and()
                .cors().and().csrf().disable()
                .apply(otpAuthenticationSecurityConfig).
                and()
                .apply(mpAuthenticationSecurityConfig)
                .and()
                .apply(tpAuthenticationSecurityConfig);
                http.headers().cacheControl();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    /**
     * 开发记住我功能
     */
    @Autowired
    private DataSource dataSource;
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


}

