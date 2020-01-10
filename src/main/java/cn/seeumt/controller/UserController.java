package cn.seeumt.controller;

import cn.seeumt.dataobject.WxUser;
import cn.seeumt.dto.MPWXUserInfoDTO;
import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.form.UserInfo;
import cn.seeumt.model.CommentContent;
import cn.seeumt.service.CommentService;
import cn.seeumt.service.UserInfoService;
import cn.seeumt.service.WxUserService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    private UserInfoService userInfoService;
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private CommentService commentService;
    @GetMapping(value = "/")
    public List<CommentContent> findMyCommentsOfAnArticle(String articleId,String userId) {
        return commentService.findUserCommentsOfAnArticle(articleId, userId);
    }

    @ApiOperation(value = "微信小程序登录",notes = "code需要通过wx.login获取",httpMethod = "POST")
    @PostMapping(value = "/mpWXLogin/{code}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO login(
                          @ApiParam(name = "code",value = "wx.login得到的code",required = true)
                          @PathVariable String code,
                          @RequestBody MPWXUserInfo mpwxUserInfo) {
        JSONObject SessionKeyAndOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        String openId = SessionKeyAndOpenId.getString("openid");
        String sessionKey = SessionKeyAndOpenId.getString("session_key");
        WxUser wxUser = wxUserService.selectByOpenId(openId);
        String skey = UuidUtil.getUUID();
        if (wxUser == null) {
            WxUser newWXUser = wxUserService.insert(mpwxUserInfo, openId, sessionKey, skey);
            MPWXUserInfoDTO mpwxUserInfoDTO = new MPWXUserInfoDTO();
            BeanUtils.copyProperties(newWXUser, mpwxUserInfoDTO);
            return ResultVO.success(mpwxUserInfoDTO,"亲，你终于来了！");
        }else {
            wxUser.setLastVisitTime(new Date());
            wxUser.setSkey(skey);
            wxUserService.update(wxUser);
        }
        MPWXUserInfoDTO mpwxUserInfoDTO = new MPWXUserInfoDTO();
        BeanUtils.copyProperties(wxUser, mpwxUserInfoDTO);
        return ResultVO.success(mpwxUserInfoDTO,"登录成功");
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
//
//    @GetMapping("/registerOrLogin")
//    public ResultVO registerOrLogin(@RequestBody UserInfo userInfo) {
//        ResultVO resultVO = userInfoService.logIn(userInfo.getUsername(), userInfo.getPassword());
//        if (resultVO.getCode() == 0) {
//            httpSession.setAttribute(httpSession.getId(), resultVO.getData());
//        }
//        return resultVO;
//    }



}
