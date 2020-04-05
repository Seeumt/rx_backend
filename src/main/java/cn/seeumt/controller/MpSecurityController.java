package cn.seeumt.controller;

import cn.seeumt.utils.WechatUtil;
import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import okhttp3.ResponseBody;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/3/27 10:37
 */
@RestController
@RequestMapping("/mp")
public class MpSecurityController {
    @GetMapping("/msg")
    public JSONObject getToken(String content) throws HttpException {
        JSONObject accessToken = WechatUtil.checkMsg(content);
        return accessToken;
    }


    @PostMapping("/img")
    public JSONObject checkImg(@RequestPart("media") MultipartFile file) throws HttpException, IOException {
        return WechatUtil.checkImg(file);

    }
}
