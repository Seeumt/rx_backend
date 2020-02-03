package cn.seeumt.security.jwt;

import cn.seeumt.dao.WxUserMapper;
import cn.seeumt.dataobject.WxUser;
import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.security.enums.SecurityEnum;
import cn.seeumt.security.exception.TokenIsExpiredException;
import cn.seeumt.security.token.MpAuthenticationToken;
import cn.seeumt.security.token.OtpAuthenticationToken;
import cn.seeumt.service.UserInfoService;
import cn.seeumt.utils.JwtTokenUtils;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/31 14:56
 */
@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Autowired
    private WxUserMapper wxUserMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        ServletContext sc = request.getSession().getServletContext();
        // 获取 spring 容器
        AbstractApplicationContext cxt = (AbstractApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
        if(cxt != null && cxt.getBean("wxUserMapper") != null && wxUserMapper == null) {
            // 取出 WxUserMapper
            wxUserMapper = (WxUserMapper) cxt.getBean("wxUserMapper");
        }
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        String type = request.getHeader("type");
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            if (type==null) {
                SecurityContextHolder.getContext().setAuthentication(getMpAuthentication(tokenHeader));
            } else if ("tel".equals(type)) {
                SecurityContextHolder.getContext().setAuthentication(getOtpAuthentication(tokenHeader));
            }
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

    // 这里从token中获取用户信息并新建一个token（XXXAuthenticationToken）
    private MpAuthenticationToken getMpAuthentication(String tokenHeader) throws TokenIsExpiredException {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        if (JwtTokenUtils.isExpiration(token)) {
            throw new TokenIsExpiredException(SecurityEnum.TOKEN_EXPIRED);
        }
        //现在正在鉴权
        String openId = JwtTokenUtils.getvalidId(token);
        if (openId != null){
            String userRoles = JwtTokenUtils.getUserRoles(token);
            List<String> rolesList = Arrays.asList(userRoles.split(","));
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String role : rolesList) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            QueryWrapper<WxUser> wrapper = new QueryWrapper<>();
            wrapper.eq("open_id", openId);
            WxUser wxUser = wxUserMapper.selectOne(wrapper);
            MPWXUserInfo mpwxUserInfo = new MPWXUserInfo();
            BeanUtils.copyProperties(wxUser,mpwxUserInfo);
            // TODO: 2020/2/2 这个是不是单单在共享信息呢，一会儿放到SecurityContextHolder中
            // TODO: 2020/2/2 authorities这个是在请求的时候进行校验即刻
            return new MpAuthenticationToken(mpwxUserInfo,authorities);
        }
        return null;
    }



    private OtpAuthenticationToken getOtpAuthentication(String tokenHeader) throws TokenIsExpiredException {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        if (JwtTokenUtils.isExpiration(token)) {
            throw new TokenIsExpiredException(SecurityEnum.TOKEN_EXPIRED);
        }
        //现在正在鉴权
            String telephone = JwtTokenUtils.getvalidId(token);
            if (telephone != null){
                String userRoles = JwtTokenUtils.getUserRoles(token);
                List<String> rolesList = Arrays.asList(userRoles.split(","));
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for (String role : rolesList) {
                    authorities.add(new SimpleGrantedAuthority(role));
                }

                return new OtpAuthenticationToken(telephone,authorities);
        }
        return null;
    }


}



