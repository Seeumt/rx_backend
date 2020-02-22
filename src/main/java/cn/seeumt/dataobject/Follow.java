package cn.seeumt.dataobject;

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
 * @since 2020-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Follow implements Serializable {


    private static final long serialVersionUID = 1725241511844090804L;
    /**
     * 关注id
     */
    @TableId
    private String followId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 关注者id
     */
    private String idolId;

    /**
     * 正在关注
     */
    private Boolean isFollow;

    /**
     * //创建时间
     */
    private Date createTime;

    /**
     * //更新时间
     */
    private Date updateTime;


}
