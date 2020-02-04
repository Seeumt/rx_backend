package cn.seeumt.controller;



import cn.seeumt.dataobject.WxUser;
import cn.seeumt.dto.MPWXUserInfoDTO;
import cn.seeumt.enums.ResultCode;
import cn.seeumt.form.LoginUser;
import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.model.OtpCode;
import cn.seeumt.model.ResponseTokenUser;
import cn.seeumt.model.UserDetail;
import cn.seeumt.service.AuthService;
import cn.seeumt.utils.KeyUtil;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.utils.WechatUtil;
import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;


@RestController
@RequestMapping("/auth")
public class AuthController {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    public ResultVO login(
            @Valid @RequestBody LoginUser loginUser){
        UserDetail userDetail = authService.upLogin(loginUser.getUsername(), loginUser.getPassword());
        return ResultVO.ok(userDetail);
    }






    @GetMapping(value = "/logouty")
    @ApiOperation(value = "登出", notes = "退出登陆")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResultVO logoutt(HttpServletRequest request){
        String token = request.getHeader(TOKEN_HEADER);
        if (token == null) {
            return ResultVO.failure(ResultCode.UNAUTHORIZED);
        }
        authService.logout(token);
        return ResultVO.ok();
    }
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
