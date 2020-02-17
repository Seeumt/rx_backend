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
 * @since 2020-02-17
 */
@Data
public class Rating implements Serializable {


    private static final long serialVersionUID = 5964914644957976338L;
    /**
     * 评分id
     */
    @TableId
    private String ratingId;

    /**
     * 评分
     */
    private Integer rating;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 根id
     */
    private String apiRootId;

    /**
     * 状态 1正常 0取消
     */
    private Boolean status;


}
