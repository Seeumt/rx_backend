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
import io.swagger.annotations.Api;
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
 * 用户
 * @author Seeumt
 * @date 2019/12/8 18:08
 */
@Api(tags = {"用户"})
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


    /**
     * 微信小程序登录
     * @param code 小程序身份码
     * @param mpwxUserInfo 小程序用户请求体
     * @return ResultVO
     */
    @ApiOperation(value = "微信小程序登录",notes = "code需要通过wx.login获取",httpMethod = "POST")
    @PostMapping(value = "/mpLogin/{code}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO mpLogin(
            @ApiParam(name = "code",value = "wx.login得到的code",required = true)
            @PathVariable String code,
            @RequestBody MpWxUserInfo mpwxUserInfo) {
        JSONObject sessionKeyAndOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        String openId = sessionKeyAndOpenId.getString("openid");
        mpwxUserInfo.setOpenId(openId);
        String sessionKey = sessionKeyAndOpenId.getString("session_key");
        log.info("【登录】用户{}进行微信授权登录成功",mpwxUserInfo.getOpenId());
        UserDetail userDetail = authService.mpLogin(mpwxUserInfo);
        return ResultVO.success(userDetail,"登录成功");
    }


    /**
     * Otp 手机验证码登录
     * @param telLogin 手机验证码登录请求体
     * @return ResultVO
     */
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

    /**
     * 手机号密码登录
     * @param loginUser  手机号密码登录请求体
     * @return 手机号密码登录
     * @throws IOException
     * @throws ServletRequestBindingException
     */
    @PostMapping("/tpLogin")
    @ApiOperation(value = "手机号密码登录",notes = "",httpMethod = "POST")
    public ResultVO otpLogin(@Valid @RequestBody LoginUser loginUser) throws IOException, ServletRequestBindingException {
        log.info("【登录】用户{}使用进行手机号密码登录",loginUser.getTelephone());
        UserDetail userDetail = authService.tpLogin(loginUser.getTelephone(), loginUser.getPassword());
        return ResultVO.success(userDetail,"登录成功");
    }

    /**
     * 用户名密码登录
     * @param loginUser 用户名密码登录请求体
     * @return ResultVO
     */
    @PostMapping(value = "/upLogin")
    @ApiOperation(value = "用户名密码登录", notes = "")
    public ResultVO upLogin(
            @Valid @RequestBody LoginUser loginUser){
        log.info("【登录】用户{}使用进行用户名密码登录",loginUser.getUsername());
        OnlineUtil.setLastOperateTimeByUsername(loginUser.getUsername());
        UserDetail userDetail = authService.upLogin(loginUser.getUsername(), loginUser.getPassword());
        return ResultVO.success(userDetail);
    }

    /**
     * 获取短信验证码
     * @param telephone 手机号
     * @param type 类型
     * @return ResultVO
     * @throws ClientException
     */
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
                return ResultVO.error(TipsFlash.TELEPHONE_HAS_BOUND);
            }
            return sendOtpCode(telephone);

        }else if (PASSWORD.equals(type)) {
            log.info("【获取短信验证码】手机号{}获取短信验证码找回密码",telephone);
            User user = userService.selectByTelephone(telephone);
            if (user == null) {
                return ResultVO.error(TipsFlash.TELEPHONE_NOT_RECORDED);
            }
            return sendOtpCode(telephone);
        }else if (TEL_LOGIN.equals(type)) {
            log.info("【获取短信验证码】手机号{}获取短信验证码登录",telephone);
            User user = userService.selectByTelephone(telephone);
            if (user == null) {
                return ResultVO.error(TipsFlash.TELEPHONE_NOT_RECORDED);
            }
            return sendOtpCode(telephone);
        }
        return ResultVO.error(TipsFlash.INVAILD_ARGUMENT);
    }

    /**
     * 重置密码
     * @param telLogin 手机号重置密码请求体
     * @return ResultVO
     */
    @PutMapping("/pwd")
    @ApiOperation(value = "重置密码",httpMethod = "PUT")
    public ResultVO resetPwd(@RequestBody TelLogin telLogin) {
        log.info("【重置密码】手机号{}进行重置密码",telLogin.getTelephone());
        userService.resetPwd(telLogin.getTelephone(), telLogin.getPassword());
        return ResultVO.success(20008, "修改密码成功！");
    }

    /**
     * 微信openId绑定手机号
     * @param openId 微信openId
     * @param telephone 手机号
     * @param code 小程序身份码
     * @return ResultVO
     * @throws ClientException
     */
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

    /**
     * 修改个人信息
     * @param mpwxUserInfoDTO 小程序个人信息请求体
     * @return ResultVO
     */
    @PostMapping(value = "/modifyUserInfo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO modifyUserInfo(@RequestBody MpWxUserInfoDTO mpwxUserInfoDTO) {
        log.info("【更改信息】{}修改个人信息",mpwxUserInfoDTO.getUserId());
        return wxUserService.modifyUserInfo(mpwxUserInfoDTO);
    }

    /**
     * 更新头像
     * @param userId 用户主键id
     * @param file 用户上传文件
     * @return ResultVO
     * @throws IOException
     */
    @PostMapping(value = "/uploadFace", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultVO uploadFace(String userId, @RequestPart("file") MultipartFile file) throws IOException {
        String originUrl = AliyunOssUtil.getOriginUrl(file);
        log.info("【修改头像】用户 {}修改头像",userId);
        ResultVO resultVO = userService.uploadFaceIcon(userId, originUrl);
        return resultVO;
    }

    /**
     * 用户进行第三方登录
     * @param loginType 登录类型
     * @param thirdPartyUser 第三方登录请求体
     * @return ResultVO
     */
    @PostMapping(value = "/thirdPartyLogin/{logintype}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO thirdPartyLogin(@PathVariable("logintype") String loginType,
                                    @RequestBody ThirdPartyUser thirdPartyUser) {
        log.info("用户进行第三方登录");
        return thirdPartyUserService.actionByLoginTypeAndThreePartyId(loginType, thirdPartyUser);

    }


    /**
     * 用户登出
     * @param userId 用户主键id
     * @return ResultVO
     */
    @PostMapping("/logout")
    public ResultVO logout(String userId) {
        log.info("用户 {}登出",userId);
        return ResultVO.success(0, userId+" 已成功退出！");
    }

    /**
     * 用户统计
     * @param gap 据此刻时间范围
     * @return ResultVO
     */
    @GetMapping("/online")
    public ResultVO online(@RequestParam(value = "gap", required = false, defaultValue = "600000") Integer gap) {
        log.info("进行用户统计");
        return userService.onlineUser(gap.longValue());
    }


    /**
     * 手机号短信验证码校验
     * @param telephone 手机号
     * @param code 短信验证码
     * @return ResultVO
     */
    @PostMapping("/valid")
    public ResultVO valid(String telephone,String code) {
        ResultVO resultVO = userService.validCode(telephone, code);
        if (!(Boolean) resultVO.getData()){
            return ResultVO.error(resultVO.getCode(), resultVO.getMsg());
        }
        return ResultVO.success(TipsFlash.VALID_OTPCODE_SUCCESS.getCode(),TipsFlash.VALID_OTPCODE_SUCCESS.getMsg());
    }


    /**
     * 发送短信验证码
     * @param telephone 手机号
     * @return ResultVO
     * @throws ClientException
     */
    public ResultVO sendOtpCode(String telephone) throws ClientException {
        OtpCode otpCode = OtpCode.createCode(600L);
        userService.addCache(telephone, otpCode.getCode().toString());
        AliyunMessageUtil.sendSms(telephone, otpCode.getCode().toString());
        return ResultVO.success("验证码已发送!");
    }




}
