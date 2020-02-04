package cn.seeumt.security.filter;

import cn.seeumt.form.LoginUser;
import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.security.token.MpAuthenticationToken;
import cn.seeumt.security.token.OtpAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/3 10:47
 */
public class MpAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private boolean postOnly = true;

    public MpAuthenticationFilter() {
        super(new AntPathRequestMatcher("/users/mpLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("认证方式不支持: " + request.getMethod()+"请求方式");
        } else {
            MPWXUserInfo mpwxUserInfo = null;
            try {
                mpwxUserInfo = this.obtainMpWxUserInfo(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            构建对象
            MpAuthenticationToken authRequest = new MpAuthenticationToken(mpwxUserInfo);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


    protected MPWXUserInfo obtainMpWxUserInfo(HttpServletRequest request) throws IOException {
         return new ObjectMapper().readValue(request.getInputStream(), MPWXUserInfo.class);
    }

    protected void setDetails(HttpServletRequest request, MpAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }




}
