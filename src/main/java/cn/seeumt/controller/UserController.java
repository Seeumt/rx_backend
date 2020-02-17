package cn.seeumt.controller;

import cn.seeumt.dataobject.User;
import cn.seeumt.dataobject.WxUser;
import cn.seeumt.dto.MPWXUserInfoDTO;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.form.*;
import cn.seeumt.model.CommentContent;
import cn.seeumt.model.OtpCode;
import cn.seeumt.model.UserDetail;
import cn.seeumt.security.exception.VaildCodeException;
import cn.seeumt.security.loginmodel.Otp;
import cn.seeumt.service.*;
import cn.seeumt.utils.*;
import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
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
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 18:08
 */
@RestController("userController")
@RequestMapping("/users")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;
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

    @ApiOperation(value = "微信小程序登录",notes = "code需要通过wx.login获取",httpMethod = "POST")
    @PostMapping(value = "/mpLogin/{code}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO mpLogin(
            @ApiParam(name = "code",value = "wx.login得到的code",required = true)
            @PathVariable String code,
            @RequestBody MPWXUserInfo mpwxUserInfo) {
        JSONObject SessionKeyAndOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        String openId = SessionKeyAndOpenId.getString("openid");
        mpwxUserInfo.setOpenId(openId);
        String sessionKey = SessionKeyAndOpenId.getString("session_key");
        UserDetail userDetail = authService.mpLogin(mpwxUserInfo);
        return ResultVO.success(userDetail,"登录成功");
    }


    @PostMapping("/otpLogin")
    @ApiOperation(value = "Otp 手机验证码登录", notes = "在过滤器中进行校验otpCode是否合法", httpMethod = "GET")
    public ResultVO otpLogin(HttpSession httpSession) throws IOException, ServletRequestBindingException {
        String telephone = (String) httpSession.getAttribute("telephone");
        UserDetail userDetail = authService.otpLogin(telephone);
        return ResultVO.success(userDetail, "登录成功");
    }

    @PostMapping("/tpLogin")
    @ApiOperation(value = "手机号密码登录",notes = "",httpMethod = "POST")
    public ResultVO otpLogin(@Valid @RequestBody LoginUser loginUser) throws IOException, ServletRequestBindingException {
        UserDetail userDetail = authService.tpLogin(loginUser.getTelephone(), loginUser.getPassword());
        return ResultVO.success(userDetail,"登录成功");
    }

    @PostMapping(value = "/upLogin")
    @ApiOperation(value = "用户名密码登录", notes = "")
    public ResultVO upLogin(
            @Valid @RequestBody LoginUser loginUser){
        UserDetail userDetail = authService.upLogin(loginUser.getUsername(), loginUser.getPassword());
        return ResultVO.success(userDetail);
    }

    @ApiOperation(value = "获取短信验证码", notes = "字符串手机号", httpMethod = "GET")
    @GetMapping(value = "/otp/{telephone}/{code}")
    public ResultVO sendSms(
            @ApiParam(name = "telephone", value = "手机号", required = true)
            @PathVariable String telephone,
            @PathVariable String code, HttpServletRequest httpServletRequest) throws ClientException {
        JSONObject SessionKeyAndOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        String openId = SessionKeyAndOpenId.getString("openid");
        String sessionKey = SessionKeyAndOpenId.getString("session_key");
        User user = userService.selectByTelephone(telephone);
        if (user == null) {
            return ResultVO.error(TipsFlash.TELEPHOEN_NOT_BINDED);
        }
        OtpCode otpCode = OtpCode.createCode(600L);
        httpServletRequest.getSession().setAttribute(telephone, otpCode);
//        AliyunMessageUtil.sendSms(telephone, otpCode.getCode().toString());
        return ResultVO.success(openId,"成功");
    }

    @PutMapping("/reset/pwd")
    @ApiOperation(value = "修改|找回密码",notes = "",httpMethod = "PUT")
    public ResultVO resetPwd(@RequestBody TelLogin telLogin) {
        OtpCode otpCode = (OtpCode) httpSession.getAttribute(telLogin.getTelephone());
        if (otpCode.getCode().equals(telLogin.getCode()) ) {
            userService.resetPwd(telLogin.getTelephone(), telLogin.getPassword());
        }
        return ResultVO.success(20008, "修改密码成功！");
    }

    @PostMapping("/bind")
    @ApiOperation(value = "微信openId绑定手机号",notes = "附带Otp验证码",httpMethod = "POST")
    public ResultVO bindTel(String openId,String telephone,Long code) {
        OtpCode otpCode = (OtpCode) httpSession.getAttribute(telephone);
        if (!otpCode.getCode().equals(code) ) {
            return ResultVO.error(20006, "验证码错误");
        }
        int i = userService.bindTel(openId, telephone);
        if (i > 0) {
            return ResultVO.success(200005, "绑定手机成功！");
        }
        return ResultVO.error(20007, "手机号绑定异常");
    }






    @PostMapping(value = "/modifyUserInfo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO modifyUserInfo(@RequestBody MPWXUserInfoDTO mpwxUserInfoDTO) {
        ResultVO resultVO = wxUserService.modifyUserInfo(mpwxUserInfoDTO);
        return resultVO;
    }

    @PostMapping(value = "/uploadFace", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultVO uploadFace(String userId, @RequestPart("file") MultipartFile file) throws IOException {
        String originUrl = AliyunOssUtil.getOriginUrl(file);
        ResultVO resultVO = userService.uploadFaceIcon(userId, originUrl);
        return resultVO;
    }

    @PostMapping(value = "/thirdPartyLogin/{logintype}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO thirdPartyLogin(@PathVariable("logintype") String loginType,
                                    @RequestBody ThirdPartyUser thirdPartyUser) {
        return thirdPartyUserService.actionByLoginTypeAndThreePartyId(loginType, thirdPartyUser);

    }


//    @PostMapping(value = "/registerOrLogin",consumes = MediaType.APPLICATION_JSON_VALUE)
////    @Cacheable(cacheNames = "user_session",key = "123456")
//    // 这是把这个resultVO放到了redis里？
//    public ResultVO login(@RequestBody UserInfo userInfo) {
//        ResultVO resultVO = userInfoService.logIn(userInfo.getUserId(), userInfo.getPassword());
//        return resultVO;
//    }


    @PostMapping("/logout")
    public ResultVO logout(String userId) {
//        httpSession.removeAttribute(httpSession.getId());
        return ResultVO.success(0, userId+" 已成功退出！");
    }

    @GetMapping("/online")
    public ResultVO online(@RequestParam(value = "gap", required = false, defaultValue = "600000") Integer gap) {
        return userService.onlineUser(gap.longValue());
    }


}
