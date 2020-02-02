package cn.seeumt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 10:26
 */
@Data
public class ImageCode {
    private Long code;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date expireTime;

    public ImageCode(Long code, Long expiration) {
        this.code = code;
        this.expireTime = new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
