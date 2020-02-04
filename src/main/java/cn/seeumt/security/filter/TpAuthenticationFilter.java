package cn.seeumt.security.filter;

import cn.seeumt.form.LoginUser;
import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.security.token.TpAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/5 0:54
 */
public class TpAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String TELEPHONE_KEY = "telephone";
    public static final String PASSWORD_KEY = "password";
    private String telephoneParameter = TELEPHONE_KEY;
    private String passwordParameter = PASSWORD_KEY;
    private boolean postOnly = true;

    public TpAuthenticationFilter() {
        super(new AntPathRequestMatcher("/userss/tpLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            LoginUser loginUser = this.obtainLoginUser(request);
            String telephone = null;
            telephone =loginUser.getTelephone() ;
            String password = null;
            password = loginUser.getPassword();
            if (telephone == null) {
                telephone = "";
            }

            if (password == null) {
                password = "";
            }

            telephone = telephone.trim();
//            1.创建一个Token类实例
            TpAuthenticationToken authRequest = new TpAuthenticationToken(telephone, password);
//            2.设置一些其余请求信息到authRequest中，如
            this.setDetails(request, authRequest);
//            3.尝试获取权限,将token1号放进去
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected void setDetails(HttpServletRequest request, TpAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    protected LoginUser obtainLoginUser(HttpServletRequest request) throws IOException {
        return new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getTelParameter() {
        return this.telephoneParameter;
    }

    public void setTelephoneParameter(String telephoneParameter) {
        Assert.hasText(telephoneParameter, "Username parameter must not be empty or null");
        this.telephoneParameter = telephoneParameter;
    }

    public final String getPasswordParameter() {
        return this.passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }
}
