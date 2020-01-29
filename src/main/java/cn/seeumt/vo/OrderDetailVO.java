package cn.seeumt.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-29
 */
@Data

public class OrderDetailVO implements Serializable {

    private static final long serialVersionUID = 7362312609746688905L;
    /**
     * 订单子表id
     */
    private Long detailId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品id
     */
    private Integer souvenirId;

    /**
     * 商品名称
     */
    private String souvenirName;

    /**
     * 商品图片地址
     */
    private String souvenirImage;

    /**
     * 生成订单时的商品单价，单位是元,保留两位小数
     */
    private BigDecimal currentUnitPrice;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品总价,单位是元,保留两位小数
     */
    private BigDecimal totalPrice;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;






}
