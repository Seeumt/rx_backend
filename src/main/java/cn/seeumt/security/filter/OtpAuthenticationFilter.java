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
public class OtpAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String TELEPHONE = "telephone";
    private String telephoneParameter = TELEPHONE;
    public static final String METHOD = "POST";
    private boolean postOnly = true;

    public OtpAuthenticationFilter() {
        super(new AntPathRequestMatcher("/users/otpLoginn", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException("认证方式不支持: " + request.getMethod()+"请求方式");
        } else {
            String telephone = this.obtainTelephone(request);
            if (telephone == null) {
                telephone = "";
            }

            telephone = telephone.trim();
            OtpAuthenticationToken authRequest = new OtpAuthenticationToken(telephone);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


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


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getTelephoneParameter() {
        return this.telephoneParameter;
    }


}
