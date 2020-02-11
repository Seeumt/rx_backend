package cn.seeumt.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/30 19:13
 */
@Data
public class SouvenirSimpleVO {
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private BigDecimal price;
    private Integer status;
    private Integer sales;
}
