package cn.seeumt.security.filter;

import cn.seeumt.form.MpWxUserInfo;
import cn.seeumt.security.token.MpAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

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

    public static final String METHOD = "POST";

    public MpAuthenticationFilter() {
        super(new AntPathRequestMatcher("/users/mpLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException("认证方式不支持: " + request.getMethod()+"请求方式");
        } else {
            MpWxUserInfo mpwxUserInfo = null;
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


    protected MpWxUserInfo obtainMpWxUserInfo(HttpServletRequest request) throws IOException {
         return new ObjectMapper().readValue(request.getInputStream(), MpWxUserInfo.class);
    }

    protected void setDetails(HttpServletRequest request, MpAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }




}
