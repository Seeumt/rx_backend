package cn.seeumt.security.jwt;

import cn.seeumt.dao.WxUserMapper;
import cn.seeumt.dataobject.WxUser;
import cn.seeumt.form.MpWxUserInfo;
import cn.seeumt.security.enums.SecurityEnum;
import cn.seeumt.security.exception.TokenIsExpiredException;
import cn.seeumt.security.token.MpAuthenticationToken;
import cn.seeumt.security.token.OtpAuthenticationToken;
import cn.seeumt.utils.JwtTokenUtils;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Autowired
    private WxUserMapper wxUserMapper;

    public static final String WX_USER_MAPPER = "wxUserMapper";
    public static final String MP = "mp";
    public static final String OTP = "otp";
    public static final String UP = "up";
    public static final String TP = "tp";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        ServletContext sc = request.getSession().getServletContext();
        // 获取 spring 容器
        AbstractApplicationContext cxt = (AbstractApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
        if(cxt != null && cxt.getBean(WX_USER_MAPPER) != null && wxUserMapper == null) {
            // 取出 WxUserMapper
            wxUserMapper = (WxUserMapper) cxt.getBean(WX_USER_MAPPER);
        }
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        //判断类型
        String type = request.getHeader("type");
        // 如果请求头中没有Authorization信息则直接放行了
        // TODO: 2020/2/5 开发需要 判断条件可以更严谨
        if ("".equals(type)||type==null||!tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            if (MP.equals(type)){
                SecurityContextHolder.getContext().setAuthentication(getMpAuthentication(tokenHeader));
            } else if (OTP.equals(type)) {
                SecurityContextHolder.getContext().setAuthentication(getOtpAuthentication(tokenHeader));
            } else if (UP.equals(type)) {
                SecurityContextHolder.getContext().setAuthentication(getUpAuthentication(tokenHeader));
            } else if (TP.equals(type)) {
                SecurityContextHolder.getContext().setAuthentication(getTpAuthentication(tokenHeader));
            } else if ("".equals(type)) {
                SecurityContextHolder.getContext().setAuthentication(null);
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




    /**
     * 这里从token中获取用户信息并新建一个token（XXXAuthenticationToken）
     * @param tokenHeader
     * @return
     * @throws TokenIsExpiredException
     */
    private MpAuthenticationToken getMpAuthentication(String tokenHeader) throws TokenIsExpiredException {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        if (JwtTokenUtils.isExpiration(token)) {
            throw new TokenIsExpiredException(SecurityEnum.TOKEN_EXPIRED);
        }
        //现在正在鉴权
        String openId = JwtTokenUtils.getValidId(token);
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
            MpWxUserInfo mpwxUserInfo = new MpWxUserInfo();
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
            String telephone = JwtTokenUtils.getValidId(token);
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


    private UsernamePasswordAuthenticationToken getUpAuthentication(String tokenHeader) throws TokenIsExpiredException {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        if (JwtTokenUtils.isExpiration(token)) {
            throw new TokenIsExpiredException(SecurityEnum.TOKEN_EXPIRED);
        }
        //现在正在鉴权
        String username = JwtTokenUtils.getValidId(token);
        if (username != null){
            String userRoles = JwtTokenUtils.getUserRoles(token);
            List<String> rolesList = Arrays.asList(userRoles.split(","));
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String role : rolesList) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return new UsernamePasswordAuthenticationToken(username,"",authorities);
        }
        return null;
    }

    private Authentication getTpAuthentication(String tokenHeader) throws TokenIsExpiredException {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        if (JwtTokenUtils.isExpiration(token)) {
            throw new TokenIsExpiredException(SecurityEnum.TOKEN_EXPIRED);
        }
        //现在正在鉴权
        String telephone = JwtTokenUtils.getValidId(token);
        if (telephone != null){
            telephone  = telephone.substring(0, telephone.length() - 1);
            String userRoles = JwtTokenUtils.getUserRoles(token);
            List<String> rolesList = Arrays.asList(userRoles.split(","));
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String role : rolesList) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return new UsernamePasswordAuthenticationToken(telephone,"",authorities);
        }
        return null;

    }


}



