package cn.seeumt.service;


import cn.seeumt.dataobject.OrderMaster;
import cn.seeumt.dto.OrderDTO;
import cn.seeumt.vo.ResultVO;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;

public interface OrderService {


    /**
     * 查询单个订单
     * @param orderId 订单id
     * @return OrderDTO
     */
    OrderDTO findOne(Long orderId);

   /**
    查询某个用户的订单列表
     */
    PageInfo<OrderDTO> list(int num, int size,String userId);

   /**
    取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

   /**
    完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);

   /**
    支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 创建订单
     * @param userId 用户id
     * @param shippingId 快递id
     * @return
     */
    ResultVO createOrder(String userId, Integer shippingId);

    ResultVO createOrderNow(String userId, Integer shippingId, Integer souvenirId,Integer count);

    /**
     * 查询出数据库中所有订单列表
     * @return
     */
//    PageInfo<OrderDTO> findList(Integer num,Integer size);

    OrderMaster insertOrderMaster(String userId, Integer shippingId, BigDecimal payment);

}
