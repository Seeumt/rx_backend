package cn.seeumt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/31 12:42
 */
@Component
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtsecretdemo";
    private static final String ISS = "rx";

    // 过期时间是3600秒，即1个小时
    private static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    // 添加角色的key
    private static final String ROLE_CLAIMS = "roles";

    private Map<String, String> tokenMap = new ConcurrentHashMap<>(32);


    // 修改一下创建token的方法
    public static String createToken(String validId, String authorities, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, authorities);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                // 这里要早set一点，放到后面会覆盖别的字段
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(validId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    public void removeToken(String token) {
        String validId = getTokenBody(token).getSubject();
        tokenMap.remove(validId);
    }



//    // 创建token
//    public static String createToken(String username, boolean isRememberMe) {
//        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
//        return Jwts.builder()
//                .signWith(SignatureAlgorithm.HS512, SECRET)
//                .setIssuer(ISS)
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
//                .compact();
//    }

    // 从token中获取用户名
    public static String getValidId(String token){
        return getTokenBody(token).getSubject();
    }

    // 是否已过期
    /**
     * 通过令牌拿到过期时间（前面的那个创建token的方法里设置过）判断是否已过期
     * @param token token
     * @return
     */
    // 是否已过期
    public static boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    /**
     * 返回token体
     * @param token token
     * @return token体
     */
    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getUserRoles(String token) {
        return (String) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody().get(ROLE_CLAIMS);
    }
}

