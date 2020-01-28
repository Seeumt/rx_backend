package cn.seeumt.vo;

import cn.seeumt.dto.CartDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/27 18:05
 */
@Data
public class CartVO {
    private List<CartDTO> cartDTOList;
    private BigDecimal cartTotalPrice;
    private Boolean allChecked;
}
