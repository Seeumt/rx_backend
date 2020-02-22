package cn.seeumt.utils;

import cn.seeumt.config.AliyunOssConfig;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Seeumt
 */
@Component
@Data
public class AliyunOssUtil {


        @Autowired
        public AliyunOssConfig aliyunOssConfig;

        private static AliyunOssUtil aliyunOssUtil;

        @PostConstruct
        public void init() {
            aliyunOssUtil = this;
            aliyunOssUtil.aliyunOssConfig = this.aliyunOssConfig;
        }



    public static String getOriginUrl(MultipartFile fileupload) throws OSSException, ClientException, IOException {


        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(AliyunOssUtil.aliyunOssUtil.aliyunOssConfig.getEndpoint(), AliyunOssUtil.aliyunOssUtil.aliyunOssConfig.getAccessKeyId(), AliyunOssUtil.aliyunOssUtil.aliyunOssConfig.getAccessKeySecret());

        // 文件桶
        String bucketName = aliyunOssUtil.aliyunOssConfig.getBucketName();
        // 文件名格式
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        // 该桶中的文件key
//        String dateString = sdf.format(new Date()) + ".jpg";// 20180322010634.jpg
        // 上传文件
        String id = UUID.randomUUID().toString().replaceAll("-","")+"."+getSuffix(fileupload);

        ossClient.putObject(bucketName, id, new ByteArrayInputStream(fileupload.getBytes()));
        // 设置URL过期时间为100年，默认这里是int型，转换为long型即可
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 100);
        // 生成URL
        URL originUrl = ossClient.generatePresignedUrl(bucketName, id, expiration);
        return originUrl.toString();
    }

    public static String getSuffix(MultipartFile fileupload){
        String originalFilename = fileupload.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        return suffix;
    }

    public static String getSuffix(URL url){
        String myUrl = url.toString();
        String suffix = myUrl.substring(myUrl.lastIndexOf("/") + 1);
        System.out.println(suffix);
        return suffix;
    }

    public static String getSuffix(String url){
        String suffix = url.substring(url.lastIndexOf("/") + 1);
        return suffix;
    }

    public static String cutSuffix(String originUrl){
        String url = originUrl.substring(0, originUrl.lastIndexOf("?"));
        return url;
    }

    public static String getDbUrl(String originUrl){
        String url = originUrl.substring(originUrl.lastIndexOf("/")+1, originUrl.lastIndexOf("?"));
        return url;
    }

    public static void main(String[] args) {


        String url = AliyunOssUtil.getDbUrl("http://seeumt.oss-cn-hangzhou.aliyuncs.com/20191009072340.jpg");
        System.out.println(url);
    }
}
