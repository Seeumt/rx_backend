package cn.seeumt.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Seeumt
 */
@Data
public class UserVO {

    private String username;
    @JsonProperty("nickName")
    private String nickname;

    private String faceIcon;


}
