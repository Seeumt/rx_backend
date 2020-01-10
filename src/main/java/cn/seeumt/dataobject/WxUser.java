package cn.seeumt.dataobject;

import java.time.LocalDateTime;
import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WxUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信用户id
     */
    private String userId;

    /**
     * 微信用户open_id
     */
    private String openId;

    /**
     * 微信用户昵称
     */
    private String nickName;

    /**
     * 微信用户性别
     */
    private Integer gender;

    /**
     * 微信用户所在城市
     */
    private String city;

    /**
     * 微信用户所在国家
     */
    private String country;

    /**
     * 微信用户头像
     */
    private String avatarUrl;

    /**
     * 微信用户语言
     */
    private String language;

    /**
     * 微信用户手机类型
     */
    private String mobile;

    /**
     * 微信用户手机号
     */
    private Integer telephone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
