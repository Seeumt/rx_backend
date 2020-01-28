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
 * @since 2020-01-27
 */
@Data
public class Cart implements Serializable {


    private static final long serialVersionUID = -7963330684685715562L;

    /**
     * 购物车id
     */
    private String cartId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 纪念品id
     */
    private String souvenirId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 是否勾选 已勾选1未勾选0
     */
    private Boolean checked;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
