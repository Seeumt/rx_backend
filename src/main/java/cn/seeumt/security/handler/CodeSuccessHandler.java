package cn.seeumt.security.handler;

import cn.seeumt.security.SecurityEnum;
import cn.seeumt.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 11:28
 */
@Component("codeSuccessHandler")
public class CodeSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 登陆成功之后会被调用
     * @param request
     * @param response
     * @param authentication 封装认证信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ResultVO resultVO = ResultVO.success(authentication);
        response.getWriter().write(new ObjectMapper().writeValueAsString(resultVO));
    }
}
