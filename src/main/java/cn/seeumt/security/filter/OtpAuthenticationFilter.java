package cn.seeumt.security.filter;

import cn.seeumt.security.token.OtpAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 17:11
 */
// TODO: 2020/2/2 222
public class OtpAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String TELEPHONE = "telephone";
//    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private String telephoneParameter = TELEPHONE;
    private boolean postOnly = true;

    public OtpAuthenticationFilter() {
        super(new AntPathRequestMatcher("/users/otpLoginn", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("认证方式不支持: " + request.getMethod()+"请求方式");
        } else {
            String telephone = this.obtainTelephone(request);
//            String password = this.obtainPassword(request);
            if (telephone == null) {
                telephone = "";
            }

//            if (password == null) {
//                password = "";
//            }

            telephone = telephone.trim();
            OtpAuthenticationToken authRequest = new OtpAuthenticationToken(telephone);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }
//    protected String obtainPassword(HttpServletRequest request) {
//        return request.getParameter(this.passwordParameter);
//    }

    protected String obtainTelephone(HttpServletRequest request) {
        return request.getParameter(this.telephoneParameter);
    }

    protected void setDetails(HttpServletRequest request, OtpAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setTelephoneParameter(String telephoneParameter) {
        Assert.hasText(telephoneParameter, "telephone 参数设置不能为空！");
        this.telephoneParameter = telephoneParameter;
    }

//    public void setPasswordParameter(String passwordParameter) {
//        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
//        this.passwordParameter = passwordParameter;
//    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getTelephoneParameter() {
        return this.telephoneParameter;
    }

//    public final String getPasswordParameter() {
//        return this.passwordParameter;
//    }
}
