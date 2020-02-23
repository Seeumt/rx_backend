package cn.seeumt.service;


import cn.seeumt.dataobject.OrderMaster;
import cn.seeumt.dto.OrderDTO;
import cn.seeumt.vo.ResultVO;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;

/**
 * @author Seeumt
 */
public interface OrderService {


    /**
     * 查询单个订单
     * @param orderId 订单id
     * @return OrderDTO
     */
    OrderDTO findOne(Long orderId);


    /**
     * 查询某个用户的订单列表
     * @param num 当前页数
     * @param size 每页数量
     * @param userId 用户id
     * @return PageInfo<OrderDTO>
     */
    PageInfo<OrderDTO> list(int num, int size,String userId);

    /**
     * 取消订单
     * @param orderDTO 订单传输对象
     * @return  OrderDTO
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
      * 完结订单
      * @param orderDTO 订单传输对象
      * @return  OrderDTO
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO 订单传输对象
     * @return  OrderDTO
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 创建订单
     * @param userId 用户id
     * @param shippingId 快递id
     * @return
     */
    ResultVO createOrder(String userId, Integer shippingId);

    /**
     * 创建订单
     * @param userId 用户id
     * @param shippingId 快递id
     * @param souvenirId 纪念品id
     * @param count 数量
     * @return ResultVO
     */
    ResultVO createOrderNow(String userId, Integer shippingId, Integer souvenirId,Integer count);


    /**
     * 加入主订单
     * @param userId 用户id
     * @param shippingId 快递id
     * @param payment 应付账款
     * @return OrderMaster
     */
    OrderMaster insertOrderMaster(String userId, Integer shippingId, BigDecimal payment);

}
