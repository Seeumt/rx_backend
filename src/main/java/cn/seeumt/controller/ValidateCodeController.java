package cn.seeumt.controller;

import cn.seeumt.exception.TipsException;
import cn.seeumt.form.LoginUser;
import cn.seeumt.model.OtpCode;
import cn.seeumt.model.ResponseTokenUser;
import cn.seeumt.model.UserDetail;
import cn.seeumt.security.token.OtpAuthenticationToken;
import cn.seeumt.service.AuthService;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * 校验验证码
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 10:29
 */
@Api(tags = {"校验验证码"})
@RestController
@RequestMapping("/code")
public class ValidateCodeController {


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 发送验证码
     * @param request 请求
     * @param telephone 手机号
     * @return OtpCode
     * @throws ServletRequestBindingException
     */
    @GetMapping("/otp")
    public OtpCode createCode(HttpServletRequest request,String telephone) throws ServletRequestBindingException {
        OtpCode otpCode = OtpCode.createCode(60L);
        request.getSession().setAttribute(telephone, otpCode);
        return otpCode;
    }

    /**
     * 手机号验证码登录校验
     * @param request 请求
     * @param response 响应
     * @return UserDetail
     * @throws IOException
     * @throws ServletRequestBindingException
     */
    @GetMapping("/telephone")
    public UserDetail codeLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        String telephone = (String) request.getSession().getAttribute("telephone");
        return authService.otpLogin(telephone);
    }



}
