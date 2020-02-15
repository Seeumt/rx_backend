package cn.seeumt.security.filter;

import cn.seeumt.model.OtpCode;
import cn.seeumt.security.exception.VaildCodeException;
import cn.seeumt.security.loginmodel.Otp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

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
        if (StringUtils.equals("/users/otpLogin", httpServletRequest.getRequestURI())
                && StringUtils.equalsAnyIgnoreCase(httpServletRequest.getMethod(), "POST")) {
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
        Otp otp = new ObjectMapper().readValue(httpServletRequest.getInputStream(), Otp.class);
        String telephone = otp.getTelephone();
        System.out.println(telephone);
        String validCode = otp.getValidCode();
        System.out.println(validCode);
        OtpCode otpCode = (OtpCode) httpServletRequest.getSession().getAttribute(telephone);
        System.out.println(otpCode);
        if (otpCode == null) {
            throw new VaildCodeException("参数异常");
        }
        if (otpCode.getExpireTime().after(new Date())) {
            if (otp.getValidCode().equals(otpCode.getCode().toString())) {
                httpServletRequest.getSession().setAttribute("telephone", otp.getTelephone());
            }
            else {
                throw new VaildCodeException("验证码错误");
            }
        }else {
            throw new VaildCodeException("验证码已过期，请重新获取");
        }

    }
}
