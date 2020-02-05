package cn.seeumt.security.jwt;

import cn.seeumt.security.enums.SecurityEnum;
import cn.seeumt.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无token情况 处理所有403响应
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/31 22:14
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResultVO resultVO = ResultVO.error(SecurityEnum.NOT_LOGIN);
        response.getWriter().write(new ObjectMapper().writeValueAsString(resultVO));
    }
}
