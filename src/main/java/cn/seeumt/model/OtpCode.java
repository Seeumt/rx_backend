package cn.seeumt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 10:26
 */
@Data
public class OtpCode {
    private Long code;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date expireTime;

    public OtpCode(Long code, Long expiration) {
        this.code = code;
        this.expireTime = new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public static OtpCode createCode(Long expiration) {
        Random random = new Random();
        Long randNum = random.nextInt(90000) + 10000L;
        return new OtpCode(randNum , expiration);
    }
}
