package cn.seeumt.controller;



import cn.seeumt.form.LoginUser;
import cn.seeumt.model.ResponseTokenUser;
import cn.seeumt.service.AuthService;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    public ResultVO login(
            @Valid @RequestBody LoginUser loginUser){
        ResponseTokenUser response = authService.login(loginUser.getUsername(), loginUser.getPassword());
        return ResultVO.ok(response);
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
