package cn.seeumt.dataobject;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-10
 */
@Data
public class ThirdPartyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户头像
     */
    private String faceIcon;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 第三方id
     */
    private String thirdPartyId;

    /**
     * 第三方名称
     */
    private String loginType;

    /**
     * 用户手机号
     */
    private Integer telephone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最新登录时间
     */
    private Date lastVisitTime;

    /**
     * session_key
     */
    private String sessionKey;

    /**
     * skey
     */
    private String skey;


}
