package cn.seeumt.utils;

import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.iharder.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Seeumt
 */
public class WechatUtil {
    public static JSONObject getSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>(20);
        // https://mp.weixin.qq.com/wxopen/devprofile?action=get_profile&token=164113089&lang=zh_CN
        //小程序appId
        requestUrlParam.put("appid", "wx24d570065aeb53e2");
        //小程序secret
        requestUrlParam.put("secret", "7b2e218c4a0725372bbdcfb3e7d93171");
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public static JSONObject getAccessToken() {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> requestUrlParam = new HashMap<>(20);
        // https://mp.weixin.qq.com/wxopen/devprofile?action=get_profile&token=164113089&lang=zh_CN
        //小程序appId
        requestUrlParam.put("appid", "wx24d570065aeb53e2");
        //小程序secret
        requestUrlParam.put("secret", "7b2e218c4a0725372bbdcfb3e7d93171");
        //默认参数
        requestUrlParam.put("grant_type", "client_credential");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doGet(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public static JSONObject checkMsg(String content) throws HttpException {
        Map<String, String> requestUrlParam = new HashMap<>(20);
        JSONObject token = getAccessToken();
        String jsonStr = "{\"content\":\"" + content + "\"}";
        String accessToken = token.getString("access_token");
        String requestUrl = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token="+accessToken;
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPostJson(requestUrl, jsonStr));
        return jsonObject;
    }



    public static JSONObject checkImg(MultipartFile file) throws HttpException, IOException {
        JSONObject token = getAccessToken();
        String accessToken = token.getString("access_token");
        String requestUrl = "https://api.weixin.qq.com/wxa/img_sec_check?access_token="+accessToken;
        return JSON.parseObject(HttpClientUtil.checkImg(file, requestUrl));
    }

    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) throws IOException {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            // 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
