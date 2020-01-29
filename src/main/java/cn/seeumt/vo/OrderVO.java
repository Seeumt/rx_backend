package cn.seeumt.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/29 16:22
 */
@Data
public class OrderVO {
    private List<OrderDetailVO> orderItemVoList;
    private BigDecimal orderTotalPrice;
}
