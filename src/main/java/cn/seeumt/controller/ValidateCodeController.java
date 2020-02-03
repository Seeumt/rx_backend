package cn.seeumt.controller;

import cn.seeumt.exception.TipsException;
import cn.seeumt.form.LoginUser;
import cn.seeumt.model.OtpCode;
import cn.seeumt.model.ResponseTokenUser;
import cn.seeumt.model.UserDetail;
import cn.seeumt.security.token.OtpAuthenticationToken;
import cn.seeumt.service.AuthService;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 10:29
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/code/otp")
    public OtpCode createCode(HttpServletRequest request,String telephone) throws ServletRequestBindingException {
//        ServletRequestUtils.getRequiredStringParameter(new ServletWebRequest(request).getRequest(), telephone);
        OtpCode otpCode = OtpCode.createCode(60L);
//        sessionStrategy.setAttribute(new ServletWebRequest(request), telephone, otpCode);
        request.getSession().setAttribute(telephone, otpCode);
        return otpCode;
    }

    @GetMapping("/code/telephone")
    public UserDetail codeLogin(HttpServletRequest request, HttpServletResponse response, Integer code) throws IOException, ServletRequestBindingException {
        // TODO: 2020/2/2 这方法厉害
//        String telephone = ServletRequestUtils.(new ServletWebRequest(request).getRequest(), "telephone");
        String telephone = (String) request.getSession().getAttribute("telephone");
        return authService.OtpLogin(telephone);
    }



}
