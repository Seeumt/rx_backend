//package cn.seeumt.security;
//
//import cn.seeumt.form.LoginUser;
//import cn.seeumt.model.ImageCode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Data;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author Seeumt
// * @version 1.0
// * @date 2020/2/2 11:05
// */
////OncePerRequestFilter保证每次只调用一次
//@Data
//public class ValidateCodeFilter2 extends OncePerRequestFilter {
//
//
//    private AuthenticationFailureHandler authenticationFailureHandler;
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        if (StringUtils.equals("/code/login", httpServletRequest.getRequestURI())
//                && StringUtils.equalsAnyIgnoreCase(httpServletRequest.getMethod(), "GET")) {
//            try {
//                if (validate(httpServletRequest)) {
//                    filterChain.doFilter(httpServletRequest,httpServletResponse);
//                    return;
//                }
//            } catch (VaildCodeException e) {
//                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
//            }
//        }
//        if (!StringUtils.equalsAnyIgnoreCase(httpServletRequest.getMethod(), "GET")) {
//            filterChain.doFilter(httpServletRequest,httpServletResponse);
//        }
//        return;
//
//    }
//
//    private Boolean validate(HttpServletRequest httpServletRequest) throws IOException {
//        LoginUser loginUser = new ObjectMapper().readValue(httpServletRequest.getInputStream(), LoginUser.class);
//        ImageCode imageCode = (ImageCode) httpServletRequest.getSession().getAttribute(loginUser.getUsername());
//
//        if (loginUser.getCode().equals(imageCode.getCode())) {
////            没用了的session记得移除掉
//            httpServletRequest.getSession().removeAttribute(loginUser.getUsername());
//            httpServletRequest.getSession().setAttribute("user",loginUser);
//            return true;
//        } else {
//            throw new VaildCodeException("验证码错误");
//        }
//    }
//}
