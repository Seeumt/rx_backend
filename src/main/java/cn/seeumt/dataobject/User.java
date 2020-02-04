package cn.seeumt.dataobject;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private String userId;

    private String username;

    private String nickname;

    private String password;

    private String telephone;

    private String faceIcon;

    private Boolean enabled;

    private Boolean locked;

    private Date createTime;

    private Date lastVisitTime;

    private Boolean isRememberMe;

    private String openId;


}
