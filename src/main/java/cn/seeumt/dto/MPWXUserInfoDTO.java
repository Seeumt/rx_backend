package cn.seeumt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MPWXUserInfoDTO {
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
}
