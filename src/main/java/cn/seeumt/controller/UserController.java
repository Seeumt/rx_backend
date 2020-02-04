package cn.seeumt.controller;

import cn.seeumt.dataobject.WxUser;
import cn.seeumt.dto.MPWXUserInfoDTO;
import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.form.TelLogin;
import cn.seeumt.form.ThirdPartyUser;
import cn.seeumt.form.UserInfo;
import cn.seeumt.model.CommentContent;
import cn.seeumt.model.OtpCode;
import cn.seeumt.model.UserDetail;
import cn.seeumt.service.*;
import cn.seeumt.utils.AliyunOssUtil;
import cn.seeumt.utils.KeyUtil;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.utils.WechatUtil;
import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.DADD;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 18:08
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private ThirdPartyUserService thirdPartyUserService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private AuthService authService;
    @GetMapping(value = "/")
    public List<CommentContent> findMyCommentsOfAnArticle(String articleId,String userId) {
        return commentService.findUserCommentsOfAnArticle(articleId, userId);
    }



    @ApiOperation(value = "获取短信验证码", notes = "字符串手机号", httpMethod = "GET")
    @GetMapping(value = "/otp/{telephone}")
    public ResultVO sendSms(
            @ApiParam(name = "telephone", value = "手机号", required = true)
            @PathVariable String telephone) {
        OtpCode otpCode = OtpCode.createCode(60L);
        httpSession.setAttribute(telephone, otpCode);
        return ResultVO.success(otpCode);
    }

    @PutMapping("/reset/pwd")
    @ApiOperation(value = "修改|找回密码",notes = "",httpMethod = "PUT")
    public ResultVO resetPwd(@RequestBody TelLogin telLogin) {
        OtpCode otpCode = (OtpCode) httpSession.getAttribute(telLogin.getTelephone());
        if (otpCode.getCode().equals(telLogin.getCode()) ) {
            userService.resetPwd(telLogin.getTelephone(), telLogin.getPassword());
        }
        return ResultVO.success(200008, "修改密码成功！");
    }

    @GetMapping("/telLogin")
    @ApiOperation(value = "手机验证码登录",notes = "在过滤器中进行校验otpCode是否合法",httpMethod = "GET")
    public UserDetail codeLogin(HttpSession httpSession) throws IOException, ServletRequestBindingException {
        // TODO: 2020/2/2 这方法厉害
//        String telephone = ServletRequestUtils.(new ServletWebRequest(request).getRequest(), "telephone");
        String telephone = (String) httpSession.getAttribute("telephone");
        return authService.OtpLogin(telephone);
    }




    @PostMapping(value = "/modifyUserInfo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO modifyUserInfo(@RequestBody MPWXUserInfoDTO mpwxUserInfoDTO) {
        ResultVO resultVO = wxUserService.modifyUserInfo(mpwxUserInfoDTO);
        return resultVO;
    }

    @PostMapping(value = "/uploadFace", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultVO uploadFace(String userId, @RequestPart("file") MultipartFile file) throws IOException {
        String originUrl = AliyunOssUtil.getOriginUrl(file);
        ResultVO resultVO = userInfoService.uploadFaceIcon(userId, originUrl);
        return resultVO;
    }

    @PostMapping(value = "/thirdPartyLogin/{logintype}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO thirdPartyLogin(@PathVariable("logintype") String loginType,
                                    @RequestBody ThirdPartyUser thirdPartyUser) {
        return thirdPartyUserService.actionByLoginTypeAndThreePartyId(loginType, thirdPartyUser);

    }


    @PostMapping(value = "/registerOrLogin",consumes = MediaType.APPLICATION_JSON_VALUE)
//    @Cacheable(cacheNames = "user_session",key = "123456")
    // 这是把这个resultVO放到了redis里？
    public ResultVO login(@RequestBody UserInfo userInfo) {
        ResultVO resultVO = userInfoService.logIn(userInfo.getUserId(), userInfo.getPassword());
        return resultVO;
    }


    @PostMapping("/logout")
    public ResultVO logout(String userId) {
//        httpSession.removeAttribute(httpSession.getId());
        return ResultVO.success(0, userId+" 已成功退出！");
    }


}
