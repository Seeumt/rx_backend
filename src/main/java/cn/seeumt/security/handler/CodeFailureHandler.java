package cn.seeumt.security.handler;

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
@Component("codeFailureHandler")
public class CodeFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ResultVO resultVO = ResultVO.error(exception.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(resultVO));
    }
}
