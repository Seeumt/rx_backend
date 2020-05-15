package cn.seeumt.controller;

import cn.seeumt.utils.WechatUtil;
import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import okhttp3.ResponseBody;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 内容安全
 * @author Seeumt
 * @version 1.0
 * @date 2020/3/27 10:37
 */
@Api(tags = {"内容安全"})
@RestController
@RequestMapping("/mp")
public class MpSecurityController {

    /**
     * 文本内容安全检测
     * @param content 内容
     * @return JSONObject
     * @throws HttpException
     */
    @GetMapping("/msg")
    public JSONObject getToken(String content) throws HttpException {
        JSONObject accessToken = WechatUtil.checkMsg(content);
        return accessToken;
    }


    /**
     * 图片内容安全检测
     * @param file 文件
     * @return JSONObject
     * @throws HttpException
     * @throws IOException
     */
    @PostMapping("/img")
    public JSONObject checkImg(@RequestPart("media") MultipartFile file) throws HttpException, IOException {
        return WechatUtil.checkImg(file);

    }
}
