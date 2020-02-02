package cn.seeumt.filter;

import cn.seeumt.security.SecurityEnum;
import cn.seeumt.security.TokenIsExpiredException;
import cn.seeumt.utils.JwtTokenUtils;
import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/31 14:56
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        } catch (TokenIsExpiredException e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ResultVO resultVO = ResultVO.error(e.getCode(), e.getMessage());
            response.getWriter().write(new ObjectMapper().writeValueAsString(resultVO));
            response.getWriter().flush();
            return;
        }
        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token（UsernamePasswordAuthenticationToken）
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws TokenIsExpiredException {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        if (JwtTokenUtils.isExpiration(token)) {
            throw new TokenIsExpiredException(SecurityEnum.TOKEN_EXPIRED);
        }
        String username = JwtTokenUtils.getUsername(token);
        if (username != null){
            String userRoles = JwtTokenUtils.getUserRoles(token);
            List<String> rolesList = Arrays.asList(userRoles.split(","));
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String role : rolesList) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            // TODO: 2020/2/2 这个是不是单单在共享信息呢，一会儿放到SecurityContextHolder中
            // TODO: 2020/2/2 authorities这个是在请求的时候进行校验即刻
            return new UsernamePasswordAuthenticationToken(username, null,authorities);
        }
        return null;
    }
}
