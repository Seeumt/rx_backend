//package cn.seeumt.controller;
//
//
//import cn.seeumt.api.User;
//import cn.seeumt.enums.ResultCode;
//import cn.seeumt.model.ResponseUserToken;
//import cn.seeumt.dataobject.Role;
//import cn.seeumt.model.UserDetail;
//import cn.seeumt.service.AuthService;
//import cn.seeumt.vo.ResultVO;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//
//@RestController
//@Api(description = "登陆注册及刷新token")
//@RequestMapping("/api/v1")
//public class AuthController {
//    @Value("${jwt.header}")
//    private String tokenHeader;
//
//    private final AuthService authService;
//
//    @Autowired
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping(value = "/login")
//    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
//    public ResultVO login(
//            @Valid @RequestBody User user){
//        final ResponseUserToken response = authService.login(user.getName(), user.getPassword());
//        return ResultVO.ok(response);
//    }
//
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
////    @GetMapping(value = "refresh")
////    @ApiOperation(value = "刷新token")
////    public ResultVO refreshAndGetAuthenticationToken(
////            HttpServletRequest request){
////        String token = request.getHeader(tokenHeader);
////        ResponseUserToken response = authService.refresh(token);
////        if(response == null) {
////            return ResultVO.failure(ResultCode.BAD_REQUEST, "token无效");
////        } else {
////            return ResultVO.ok(response);
////        }
////    }
//}
