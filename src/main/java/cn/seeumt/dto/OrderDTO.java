package cn.seeumt.dto;

import cn.seeumt.dataobject.Shipping;
import cn.seeumt.vo.OrderDetailVO;
import cn.seeumt.vo.ShippingVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/29 11:56
 */
@Data
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 6875339417615309926L;
    private Long orderId;
    private String userId;
    private BigDecimal payment;
    private Integer paymentType;
    private String paymentTypeDesc;
    private Integer postage;
    private Integer status;
    private String statusDesc;
    @JsonProperty("shipping")
    private ShippingVO shippingVO;
    @JsonProperty("orderDetailList")
    private List<OrderDetailVO> orderDetailVOList;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date paymentTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sendTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date closeTime;


}
