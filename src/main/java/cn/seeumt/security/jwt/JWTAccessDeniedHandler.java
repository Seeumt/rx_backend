package cn.seeumt.security.jwt;

import cn.seeumt.security.enums.SecurityEnum;
import cn.seeumt.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有访问权限(有token)
 * @author: Seeumt
 * @date: 2020 2/1
 */
public class JWTAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResultVO resultVO = ResultVO.error(SecurityEnum.LACK_OF_AUTHORITY);
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(resultVO));
    }
}
