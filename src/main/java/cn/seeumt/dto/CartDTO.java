package cn.seeumt.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDTO {
    /**
     * id
     */
    private Integer cartId;
    /**
     * userId
     */
    private String userId;

    /**
     * souvenirId
     */
    private Integer souvenirId;

    /**
     * souvenirName 要通过souvenir_id 查询后set
     */
    private String name;

    /**
     * quantity
     */
    private Integer quantity;

    /**
     * 是否勾选
     */
    private Boolean checked;

    /*
     ========以下是Souvenir=======
     */
    /**
     * 纪念品副标题
     */
    private String subtitle;

    /**
     * 主图url相对地址
     */
    private String mainImage;

    /**
     * 纪念品价格
     */
    private BigDecimal price;

    /**
     * 商品状态.1-在售 2-下架 3-删除
     */
    private Integer status;

    /**
     * 库存
     */
    private Integer stock;


    /*
      ===========额外==============
     */
    /**
     * 总价
     */
    private BigDecimal cartItemTotalPrice;


    /**
     * 限制数量的一个返回结果
     */
    private String limitQuantity;



}
