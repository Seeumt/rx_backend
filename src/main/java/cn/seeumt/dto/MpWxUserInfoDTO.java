package cn.seeumt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Seeumt
 */
@Data
public class MpWxUserInfoDTO {
    private String userId;
    @JsonProperty("faceIcon")
    private String avatarUrl;
    private String city;
    private String country;
    private Integer gender;
    private String language;
    @JsonProperty("nickname")
    private String nickName;
    private String province;
    private String skey;
    private String sessionKey;
    @JsonProperty("birthday")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
}
