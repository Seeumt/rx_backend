package cn.seeumt.controller;

import cn.seeumt.form.LoginUser;
import cn.seeumt.model.ImageCode;
import cn.seeumt.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 10:29
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

//    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @PostMapping("/code/getCode")
    public ImageCode createCode(HttpServletRequest request,String username) throws IOException {
        ImageCode imageCode = createCode();
//        sessionStrategy.setAttribute(new ServletWebRequest(request), username, imageCode);
        request.getSession().setAttribute(username,imageCode);
        return imageCode;
    }

    private ImageCode createCode() {
        Random random = new Random();
        Long randNum = random.nextInt(90000) + 10000L;
        System.out.println(randNum);
        return new ImageCode(randNum , 15L);
    }

    @GetMapping("/code/login")
    public ResultVO codeLogin(HttpServletRequest request, HttpServletResponse response, Integer code) throws IOException {
        // TODO: 2020/2/2 这方法厉害
//        ServletRequestUtils.getIntParameter(new ServletWebRequest(request).getRequest(), "", "");
        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
        return ResultVO.success(loginUser);
    }

}
