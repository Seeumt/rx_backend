//package cn.seeumt.config;
//
///**
// * @author Seeumt
// * @date 2019/12/15 22:04
// */
//import net.sf.json.JSON;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//
//@Configuration
//public class SessionConfig implements WebMvcConfigurer{
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SecurityInterceptor())
//                //排除拦截
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/logout")
//
//                //拦截路径
//                .addPathPatterns("/**");
//    }
//
//
//    @Configuration
//    public class SecurityInterceptor implements HandlerInterceptor{
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//            HttpSession session = request.getSession();
//            if (session.getAttribute(session.getId()) != null){
//                return true;
//            }
//            response.getWriter().write("请登录");
//            return false;
//        }
//    }
//}
