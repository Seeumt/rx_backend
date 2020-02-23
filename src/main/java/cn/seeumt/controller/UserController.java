package cn.seeumt.controller;

import cn.seeumt.dataobject.User;
import cn.seeumt.dto.MpWxUserInfoDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.form.*;
import cn.seeumt.model.CommentContent;
import cn.seeumt.model.OtpCode;
import cn.seeumt.model.UserDetail;
import cn.seeumt.service.*;
import cn.seeumt.utils.*;
import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 18:08
 */
@RestController("userController")
@RequestMapping("/users")
@Slf4j
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


    public static final String PASSWORD = "password";
    public static final String TEL_LOGIN = "tp";


    @ApiOperation(value = "微信小程序登录",notes = "code需要通过wx.login获取",httpMethod = "POST")
    @PostMapping(value = "/mpLogin/{code}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO mpLogin(
            @ApiParam(name = "code",value = "wx.login得到的code",required = true)
            @PathVariable String code,
            @RequestBody MpWxUserInfo mpwxUserInfo) {
        log.info("【登录】用户{}进行微信授权登录",mpwxUserInfo.getOpenId());
        JSONObject sessionKeyAndOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        String openId = sessionKeyAndOpenId.getString("openid");
        mpwxUserInfo.setOpenId(openId);
        String sessionKey = sessionKeyAndOpenId.getString("session_key");
        UserDetail userDetail = authService.mpLogin(mpwxUserInfo);
        return ResultVO.success(userDetail,"登录成功");
    }


    @PostMapping("/otpLogin")
    @ApiOperation(value = "Otp 手机验证码登录", notes = "在过滤器中进行校验otpCode是否合法", httpMethod = "GET")
    public ResultVO otpLogin(@RequestBody TelLogin telLogin){
        log.info("【登录】用户使用{}进行手机验证码登录",telLogin.getTelephone());
        ResultVO resultVO = userService.validCode(telLogin.getTelephone(), telLogin.getCode());
        if (!(Boolean) resultVO.getData()){
            return ResultVO.error(resultVO.getCode(), resultVO.getMsg());
        }
        UserDetail userDetail = authService.otpLogin(telLogin.getTelephone());
        return ResultVO.success(userDetail, "登录成功");
    }

    @PostMapping("/tpLogin")
    @ApiOperation(value = "手机号密码登录",notes = "",httpMethod = "POST")
    public ResultVO otpLogin(@Valid @RequestBody LoginUser loginUser) throws IOException, ServletRequestBindingException {
        log.info("【登录】用户{}使用进行手机号密码登录",loginUser.getTelephone());
        UserDetail userDetail = authService.tpLogin(loginUser.getTelephone(), loginUser.getPassword());
        return ResultVO.success(userDetail,"登录成功");
    }

    @PostMapping(value = "/upLogin")
    @ApiOperation(value = "用户名密码登录", notes = "")
    public ResultVO upLogin(
            @Valid @RequestBody LoginUser loginUser){
        log.info("【登录】用户{}使用进行用户名密码登录",loginUser.getUsername());
        OnlineUtil.setLastOperateTimeByUsername(loginUser.getUsername());
        UserDetail userDetail = authService.upLogin(loginUser.getUsername(), loginUser.getPassword());
        return ResultVO.success(userDetail);
    }

    @ApiOperation(value = "获取短信验证码", notes = "字符串手机号", httpMethod = "GET")
    @GetMapping(value = "/otp/{telephone}")
    public ResultVO sendSms(
            @ApiParam(name = "telephone", value = "手机号", required = true)
            @PathVariable String telephone,
            @RequestParam(value = "type", required = false, defaultValue = "") String type) throws ClientException {
        if ("".equals(type)) {
            log.info("【获取短信验证码】手机号{}获取短信验证码",telephone);
            User user = userService.selectByTelephone(telephone);
            if (user != null) {
                return ResultVO.error(TipsFlash.TELEPHONE_HAS_BINDED);
            }
            return sendOtpCode(telephone);

        }else if (PASSWORD.equals(type)) {
            log.info("【获取短信验证码】手机号{}获取短信验证码找回密码",telephone);
            User user = userService.selectByTelephone(telephone);
            if (user == null) {
                return ResultVO.error(TipsFlash.TELEPHONE_NOT_RECORED);
            }
            return sendOtpCode(telephone);
        }else if (TEL_LOGIN.equals(type)) {
            log.info("【获取短信验证码】手机号{}获取短信验证码登录",telephone);
            User user = userService.selectByTelephone(telephone);
            if (user == null) {
                return ResultVO.error(TipsFlash.TELEPHONE_NOT_RECORED);
            }
            return sendOtpCode(telephone);
        }
        return ResultVO.error(TipsFlash.INVAILD_ARGUMENT);
    }

    @PutMapping("/pwd")
    @ApiOperation(value = "重置密码",httpMethod = "PUT")
    public ResultVO resetPwd(@RequestBody TelLogin telLogin) {
        log.info("【重置密码】手机号{}进行重置密码",telLogin.getTelephone());
        userService.resetPwd(telLogin.getTelephone(), telLogin.getPassword());
        return ResultVO.success(20008, "修改密码成功！");
    }

    @PostMapping("/bind")
    @ApiOperation(value = "微信openId绑定手机号", notes = "附带Otp验证码", httpMethod = "POST")
    public ResultVO bindTel(String openId, String telephone, String code) throws ClientException {
        log.info("【绑定手机】尝试将手机号{}与{}进行绑定",telephone,openId);
        ResultVO resultVO = userService.validCode(telephone, code);
        if (!(Boolean) resultVO.getData()) {
            log.info("【绑定手机】手机号{}与{}绑定成功",telephone,openId);
            return ResultVO.error(resultVO.getCode(), resultVO.getMsg());
        }
        int i = userService.bindTel(openId, telephone);
        if (i > 0) {
            log.info("【绑定手机】手机号{}与{}绑定成功",telephone,openId);
            return ResultVO.success(telephone, Tips.BIND_SUCCESS.getMsg());
        }
        return ResultVO.error(TipsFlash.BIND_TELEPHONE_EXCEPTION);
    }

    @PostMapping(value = "/modifyUserInfo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO modifyUserInfo(@RequestBody MpWxUserInfoDTO mpwxUserInfoDTO) {
        log.info("【更改信息】{}修改个人信息",mpwxUserInfoDTO.getUserId());
        return wxUserService.modifyUserInfo(mpwxUserInfoDTO);
    }

    @PostMapping(value = "/uploadFace", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultVO uploadFace(String userId, @RequestPart("file") MultipartFile file) throws IOException {
        String originUrl = AliyunOssUtil.getOriginUrl(file);
        log.info("【修改头像】用户 {}修改头像",userId);
        ResultVO resultVO = userService.uploadFaceIcon(userId, originUrl);
        return resultVO;
    }

    @PostMapping(value = "/thirdPartyLogin/{logintype}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO thirdPartyLogin(@PathVariable("logintype") String loginType,
                                    @RequestBody ThirdPartyUser thirdPartyUser) {
        log.info("用户进行第三方登录");
        return thirdPartyUserService.actionByLoginTypeAndThreePartyId(loginType, thirdPartyUser);

    }


    @PostMapping("/logout")
    public ResultVO logout(String userId) {
        log.info("用户 {}登出",userId);
        return ResultVO.success(0, userId+" 已成功退出！");
    }

    @GetMapping("/online")
    public ResultVO online(@RequestParam(value = "gap", required = false, defaultValue = "600000") Integer gap) {
        log.info("进行用户统计");
        return userService.onlineUser(gap.longValue());
    }


    @PostMapping("/valid")
    public ResultVO valid(String telephone,String code) {
        ResultVO resultVO = userService.validCode(telephone, code);
        if (!(Boolean) resultVO.getData()){
            return ResultVO.error(resultVO.getCode(), resultVO.getMsg());
        }
        return ResultVO.success(TipsFlash.VALID_OTPCODE_SUCCESS.getCode(),TipsFlash.VALID_OTPCODE_SUCCESS.getMsg());
    }


    public ResultVO sendOtpCode(String telephone) throws ClientException {
        OtpCode otpCode = OtpCode.createCode(600L);
        userService.addCache(telephone, otpCode.getCode().toString());
        AliyunMessageUtil.sendSms(telephone, otpCode.getCode().toString());
        return ResultVO.success("验证码已发送!");
    }




}
