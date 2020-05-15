package cn.seeumt.controller;



import cn.seeumt.enums.ResultCode;
import cn.seeumt.form.LoginUser;
import cn.seeumt.model.UserDetail;
import cn.seeumt.service.AuthService;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 授权
 * @author Seeumt
 */
@Api(tags = {"权限接口"})
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    @Autowired
    private AuthService authService;

    /**
     * 登陆
     * @param loginUser 登录用户请求体
     * @return ResultVO
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    public ResultVO login(
            @Valid @RequestBody LoginUser loginUser){
        log.info("【用户登录】用户{}通过用户名密码方式登录",loginUser.getUsername());
        UserDetail userDetail = authService.upLogin(loginUser.getUsername(), loginUser.getPassword());
        return ResultVO.ok(userDetail);
    }



    @GetMapping(value = "/logouty")
    @ApiOperation(value = "登出", notes = "退出登陆")
    @PreAuthorize("hasAuthority('ROLE_USER')")
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
