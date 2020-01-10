package cn.seeumt.form;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-10
 */
@Data
public class ThirdPartyUser{

    private String faceIcon;

    private String nickName;

    private String thirdPartyId;

    private String loginType;

    private Integer telephone;

    private String city;

    private String country;

    private Integer gender;

    private String language;

    private String province;




}
