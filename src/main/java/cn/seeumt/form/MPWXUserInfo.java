package cn.seeumt.form;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class MPWXUserInfo {
    private String avatarUrl;
    private String city;
    private String country;
    private Integer gender;
    private String language;
    private String nickName;
    private String province;
}
