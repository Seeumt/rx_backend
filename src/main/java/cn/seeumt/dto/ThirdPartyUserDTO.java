package cn.seeumt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-10
 */
@Data
public class ThirdPartyUserDTO {


    private String faceIcon;

    @JsonProperty("nickname")
    private String nickName;

    private String thirdPartyId;







}
