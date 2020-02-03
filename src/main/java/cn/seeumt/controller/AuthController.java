package cn.seeumt.controller;



import cn.seeumt.dataobject.WxUser;
import cn.seeumt.dto.MPWXUserInfoDTO;
import cn.seeumt.form.LoginUser;
import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.model.ResponseTokenUser;
import cn.seeumt.model.UserDetail;
import cn.seeumt.service.AuthService;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.utils.WechatUtil;
import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    public ResultVO login(
            @Valid @RequestBody LoginUser loginUser){
        UserDetail userDetail = authService.login(loginUser.getUsername(), loginUser.getPassword());
        return ResultVO.ok(userDetail);
    }

    @ApiOperation(value = "微信小程序登录",notes = "code需要通过wx.login获取",httpMethod = "POST")
    @PostMapping(value = "/mpLogin/{code}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO login(
            @ApiParam(name = "code",value = "wx.login得到的code",required = true)
            @PathVariable String code,
            @RequestBody MPWXUserInfo mpwxUserInfo) {
        JSONObject SessionKeyAndOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        String openId = SessionKeyAndOpenId.getString("openid");
        mpwxUserInfo.setOpenId(openId);
//        String sessionKey = SessionKeyAndOpenId.getString("session_key");
        UserDetail userDetail = authService.MpLogin(mpwxUserInfo);
        return ResultVO.success(userDetail,"登录成功");
    }

//    @GetMapping(value = "/logout")
//    @ApiOperation(value = "登出", notes = "退出登陆")
//    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
//    public ResultVO logout(HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        if (token == null) {
//            return ResultVO.failure(ResultCode.UNAUTHORIZED);
//        }
//        authService.logout(token);
//        return ResultVO.ok();
//    }
//
//    @GetMapping(value = "/user")
//    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息")
//    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
//    public ResultVO getUser(HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        if (token == null) {
//            return ResultVO.failure(ResultCode.UNAUTHORIZED);
//        }
//        UserDetail userDetail = authService.getUserByToken(token);
//        return ResultVO.success(userDetail);
//    }
//
//    @PostMapping(value = "/sign")
//    @ApiOperation(value = "用户注册")
//    public ResultVO sign(@RequestBody User user) {
//        if (StringUtils.isAnyBlank(user.getName(), user.getPassword())) {
//            return ResultVO.failure(ResultCode.BAD_REQUEST);
//        }
//        UserDetail userDetail = new UserDetail(user.getName(), user.getPassword(), Role.builder().id(1l).build());
//        return ResultVO.ok(authService.register(userDetail));
//    }
//    @GetMapping(value = "refresh")
//    @ApiOperation(value = "刷新token")
//    public ResultVO refreshAndGetAuthenticationToken(
//            HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        ResponseUserToken response = authService.refresh(token);
//        if(response == null) {
//            return ResultVO.failure(ResultCode.BAD_REQUEST, "token无效");
//        } else {
//            return ResultVO.ok(response);
//        }
//    }
}
