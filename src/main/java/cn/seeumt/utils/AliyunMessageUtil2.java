package cn.seeumt.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @author Seeumt
 */
public class AliyunMessageUtil2 {

    private static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    /**
     * a
     */
    private static final String ACCESS_KEY_ID = "LTAI4FcZYFjsNz3dNDMMuJaF";
    /**
     * k
     */
        private static final String ACCESS_KEY_SECRET = "HepRmKtAoWYThSUXc91hi8jVaeXqj7";

        public static SendSmsResponse sendSms(String telphone,String password) throws com.aliyuncs.exceptions.ClientException {

            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(telphone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("大学生出游助手");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode("SMS_184106105");
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"code\":\"" + password + "\"}");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            return sendSmsResponse;
        }

}



