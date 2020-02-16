package cn.seeumt.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/30 19:13
 */
@Data
public class SouvenirSimpleVO {
    @JsonProperty("goods_id")
    private Integer souvenirId;
    private Integer categoryId;
    private String name;
    private String subtitle;
    @JsonProperty("img")
    private String mainImage;
    private Integer number;
    private BigDecimal price;
    private Integer status;
    @JsonProperty("slogan")
    private Integer sales;
}
