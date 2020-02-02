package cn.seeumt.security;

import cn.seeumt.form.LoginUser;
import cn.seeumt.model.ImageCode;
import cn.seeumt.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 11:05
 */
//OncePerRequestFilter保证每次只调用一次
@Data
public class ValidateCodeFilter extends OncePerRequestFilter {


    private AuthenticationFailureHandler authenticationFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/code/login", httpServletRequest.getRequestURI())
                && StringUtils.equalsAnyIgnoreCase(httpServletRequest.getMethod(), "GET")) {
            try {
                validate(httpServletRequest);

            } catch (VaildCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
            filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    private void validate(HttpServletRequest httpServletRequest) throws IOException {
        LoginUser loginUser = new ObjectMapper().readValue(httpServletRequest.getInputStream(), LoginUser.class);
        ImageCode imageCode = (ImageCode) httpServletRequest.getSession().getAttribute(loginUser.getUsername());

        if (imageCode.getExpireTime().after(new Date())) {
            if (loginUser.getCode().equals(imageCode.getCode())) {
                httpServletRequest.getSession().setAttribute("user",loginUser);
            } else {
                throw new VaildCodeException("验证码错误");
            }
        }else {
            throw new VaildCodeException("验证码已过期，请重新获取");
        }

    }
}
