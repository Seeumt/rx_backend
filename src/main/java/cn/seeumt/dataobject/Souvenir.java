package cn.seeumt.dataobject;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-01-28
 */
@Data
public class Souvenir implements Serializable {


    private static final long serialVersionUID = 9157886091037614936L;
    /**
     * 纪念品id
     */
    @TableId(value = "souvenir_id", type = IdType.AUTO)
    private Integer souvenirId;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 纪念品名称
     */
    private String name;

    /**
     * 纪念品副标题
     */
    private String subtitle;

    /**
     * 主图url相对地址
     */
    private String mainImage;

    /**
     * 图片地址,json格式,扩展用
     */
    private String subImages;

    /**
     * 详情
     */
    private String detail;

    /**
     * 纪念品价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 景区id
     */
    private String scenicId;

    /**
     * 商品状态.1-在售 2-下架 3-删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
