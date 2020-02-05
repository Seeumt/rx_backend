package cn.seeumt.aspect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Seeumt
 * @version v1
 */
@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     *  定义一个公共的方法，实现服用
     *  拦截CarouselController下面的所有方法
     *  拦截CarouselController下面的carousels方法里的任何参数(..表示拦截任何参数)
     *  写法：@Before("execution(public * cn.seeumt.controller.carousels.carousels(..))")
     */
//    @Pointcut("execution(public * cn.seeumt.controller.*Controller.*(..))")
    @Pointcut("execution(public * cn.seeumt.controller.CarouselController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        Map params = new HashMap(10);
        params.put("url", request.getRequestURL());
        params.put("uri", request.getRequestURI());
        params.put("method", request.getMethod());
        params.put("ip", request.getRemoteAddr());
        params.put("className", joinPoint.getSignature().getDeclaringTypeName());
        params.put("classMethod", joinPoint.getSignature().getName());
        params.put("args", joinPoint.getArgs());
        // 输出格式化后的json字符串
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        logger.info("请求参数: {}", gson.toJson(params));
    }

    @After("log()")
    public void doAfter() {
        logger.info("doAfter");
    }

    /**
     * 获取响应返回值
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("RESPONSE: {}", object.toString());
    }
}
