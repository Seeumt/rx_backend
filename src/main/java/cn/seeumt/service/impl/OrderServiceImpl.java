package cn.seeumt.service.impl;
import java.util.Date;

import cn.seeumt.dao.*;
import cn.seeumt.dataobject.*;
import cn.seeumt.dto.OrderDTO;
import cn.seeumt.enums.TipsBusiness;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.CartService;
import cn.seeumt.service.OrderService;
import cn.seeumt.utils.BigDecimalUtil;
import cn.seeumt.utils.EnumUtil;
import cn.seeumt.utils.KeyUtil;
import cn.seeumt.vo.OrderDetailVO;
import cn.seeumt.vo.ResultVO;
import cn.seeumt.vo.ShippingVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Seeumt
 */
@Service
@Slf4j
@Transactional(rollbackFor = TipsException.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ShippingMapper shippingMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private SouvenirMapper souvenirMapper;
    @Autowired
    private CartMapper cartMapper;

    /**
     * 【结算购物车->生成订单】
     * @param userId 用户id
     * @param shippingId 快递id
     * @return ResultVO
     */
    @Override
    public ResultVO createOrder(String userId, Integer shippingId) {
        List<Cart> carts = cartService.selectByUserId(userId);
        ResultVO resultVO = getVaildCartItem(userId, carts);
        if (resultVO.getCode() != 0) {
            return resultVO;
        }
        List<OrderDetail> orderDetailList = (List<OrderDetail>) resultVO.getData();
        BigDecimal payment = this.getOrderDetailListTotalPrice(orderDetailList);
        //生成主订单
        OrderMaster orderMaster = insertOrderMaster(userId, shippingId, payment);
        if(orderMaster == null){
            throw new TipsException(TipsFlash.ADD_TO_ORDER_MASTER_FAILED);
        }
        if(CollectionUtils.isEmpty(orderDetailList)){
            return ResultVO.error(TipsBusiness.EMPTY_CART);
        }

        for(OrderDetail orderDetail : orderDetailList){
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderMaster.getOrderId());
            orderDetail.setCreateTime(new Date());
            orderDetail.setUpdateTime(new Date());
            orderDetailMapper.insert(orderDetail);
            //生成成功,减少产品的库存
            this.reduceSouvenirStock(orderDetail);
            //清空购物车
            this.cleanCart(userId,orderDetail.getSouvenirId());
        }
        //返回给前端数据
        return ResultVO.success(findOne(orderMaster.getOrderId()));
    }

    /**
     *
     * @param userId 用户id
     * @param shippingId 快递id
     * @param souvenirId 纪念品id
     * @param count 商品数量
     * @return ResultVO
     */
    @Override
    public ResultVO createOrderNow(String userId, Integer shippingId, Integer souvenirId,Integer count) {
        Souvenir souvenir = souvenirMapper.selectById(souvenirId);
        if (souvenir != null) {
            if (!souvenir.getStatus().equals(TipsBusiness.ON_SALE.getCode())) {
                return ResultVO.error(TipsBusiness.NOT_ON_SALE);
            }
            if (souvenir.getStock() < count) {
                return ResultVO.error(TipsBusiness.LOW_STOCKS);
            }
            BigDecimal payment = new BigDecimal("0");
            payment = BigDecimalUtil.add(payment.doubleValue(), BigDecimalUtil.mul(souvenir.getPrice().doubleValue(), count.doubleValue()).doubleValue());
            OrderMaster orderMaster = createOrderMaster(userId, shippingId, payment);
            int insert = orderMasterMapper.insert(orderMaster);
            if (insert < 0) {
                throw new TipsException(TipsFlash.ADD_TO_ORDER_MASTER_FAILED);
            }
            OrderDetail orderDetail = createOrderDetail(userId, souvenir, orderMaster.getOrderId(), payment, count);
            int insert1 = orderDetailMapper.insert(orderDetail);
            if (insert1 < 0) {
                throw new TipsException(TipsFlash.ADD_TO_ORDER_DETAIL_FAILED);
            }
            log.info("【订单-立即购买】用户user_id={}购买了souvenir_id={} {}件", userId, souvenirId, count);
            return ResultVO.success(findOne(orderMaster.getOrderId()));
        }
        return ResultVO.error(TipsBusiness.SOUVENIR_NOT_EXIST);
    }

    /**
     * 【结算购物车后->清空购物车】
     * @param userId 用户id
     * @param souvenirId 纪念品id
     */
    private void cleanCart(String userId, Integer souvenirId) {
        int i = cartService.deleteByUserIdAndSouvenirId(userId, souvenirId);
        if (i < 0) {
            throw new TipsException(TipsFlash.CLEAN_CART_FAILED);
        }
    }

    /**
     * 【结算购物车后->减库存】
     * @param orderDetail 子订单
     */
    private void reduceSouvenirStock(OrderDetail orderDetail) {
        Souvenir souvenir = souvenirMapper.selectById(orderDetail.getSouvenirId());
        souvenir.setStock(souvenir.getStock() - orderDetail.getQuantity());
        souvenirMapper.updateById(souvenir);
    }

    /**
     * 【结算购物车-计算购物车总价（orderDetail叠加）】
     * @param orderDetailList 用户
     * @return
     */
    private BigDecimal getOrderDetailListTotalPrice(List<OrderDetail> orderDetailList) {
        BigDecimal payment = new BigDecimal("0");
        for(OrderDetail orderDetail : orderDetailList){
            payment = BigDecimalUtil.add(payment.doubleValue(),orderDetail.getTotalPrice().doubleValue());
        }
        return payment;
    }

    /**
     *  【结算购物车->找出有效CartItem并放入到一个子预订单列表里List<OrderDetail>】
     * @param userId
     * @param carts
     * @return ResultVO
     */
    private ResultVO getVaildCartItem(String userId, List<Cart> carts) {
        List<OrderDetail> orderDetailList = Lists.newArrayList();
        if(CollectionUtils.isEmpty(carts)){
            return ResultVO.error(TipsBusiness.EMPTY_CART);
        }
        //校验购物车的数据,包括产品的状态和数量
        for(Cart cartItem : carts){
            OrderDetail vaildOrderDetail = new OrderDetail();
            Souvenir souvenir = souvenirMapper.selectById(cartItem.getSouvenirId());
            if(!TipsBusiness.ON_SALE.getCode().equals(souvenir.getStatus())){
                return ResultVO.error(TipsBusiness.NOT_ON_SALE);
            }
            //校验库存
            if(cartItem.getQuantity() > souvenir.getStock()){
                return ResultVO.error(TipsBusiness.LOW_STOCKS);
            }
            vaildOrderDetail.setUserId(userId);
            vaildOrderDetail.setSouvenirId(souvenir.getSouvenirId());
            vaildOrderDetail.setSouvenirName(souvenir.getName());
            vaildOrderDetail.setSouvenirImage(souvenir.getMainImage());
            vaildOrderDetail.setCurrentUnitPrice(souvenir.getPrice());
            vaildOrderDetail.setQuantity(cartItem.getQuantity());
            vaildOrderDetail.setTotalPrice(BigDecimalUtil.mul(souvenir.getPrice().doubleValue(),cartItem.getQuantity()));
            orderDetailList.add(vaildOrderDetail);
        }
        return ResultVO.success(orderDetailList);
    }

    @Override
    public OrderDTO findOne(Long orderId) {
        QueryWrapper<OrderMaster> masterQueryWrapper = new QueryWrapper<>();
        masterQueryWrapper.eq("order_id", orderId);
        OrderMaster orderMaster = orderMasterMapper.selectOne(masterQueryWrapper);
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setStatusDesc(Objects.requireNonNull(EnumUtil.getByCode(orderMaster.getStatus(), TipsBusiness.class)).getMsg());
        orderDTO.setPaymentTypeDesc(Objects.requireNonNull(EnumUtil.getByCode(orderMaster.getPaymentType(), TipsBusiness.class)).getMsg());
        QueryWrapper<OrderDetail> detailQueryWrapper = new QueryWrapper<>();
        detailQueryWrapper.eq("order_id", orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectList(detailQueryWrapper);
        List<OrderDetailVO> orderDetailVOList = orderDetails.stream().map(orderDetail -> {
            OrderDetailVO orderDetailVO = new OrderDetailVO();
            BeanUtils.copyProperties(orderDetail, orderDetailVO);
            return orderDetailVO;
        }).collect(Collectors.toList());
        orderDTO.setOrderDetailVOList(orderDetailVOList);
        QueryWrapper<Shipping> shippingQueryWrapper = new QueryWrapper<>();
        shippingQueryWrapper.eq("shipping_id", orderMaster.getShippingId());
        Shipping shipping = shippingMapper.selectOne(shippingQueryWrapper);
        if (shipping != null) {
            ShippingVO shippingVO = new ShippingVO();
            BeanUtils.copyProperties(shipping, shippingVO);
            orderDTO.setShippingVO(shippingVO);
        }else {
            orderDTO.setShippingVO(null);
        }
        return orderDTO;
    }

    @Override
    public PageInfo<OrderDTO> list(int num, int size,String userId) {
        QueryWrapper<OrderMaster> masterQueryWrapper = new QueryWrapper<>();
        masterQueryWrapper.eq("user_id", userId);
        PageHelper.startPage(num, size);
        List<OrderMaster> orderMasters = orderMasterMapper.selectList(masterQueryWrapper);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasters) {
            orderDTOList.add(findOne(orderMaster.getOrderId()));
        }
        PageInfo<OrderDTO> orderDtoPageInfo = new PageInfo<>(orderDTOList);
        return orderDtoPageInfo;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 【复用】增加orderMaster记录
     * @param userId 用户id
     * @param shippingId 快递id
     * @param payment 应付款
     * @return OrderMaster
     */
    @Override
    public OrderMaster insertOrderMaster(String userId, Integer shippingId, BigDecimal payment) {
        OrderMaster orderMaster = createOrderMaster(userId, shippingId, payment);
        int insert = orderMasterMapper.insert(orderMaster);
        if (insert > 0) {
            return orderMaster;
        }
        return null;
    }

    /**
     *【复用】 创建orderMaster
     * @param userId 用户id
     * @param shippingId 快递id
     * @param payment 应付款
     * @return OrderMaster
     */
    private OrderMaster createOrderMaster(String userId, Integer shippingId, BigDecimal payment) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(KeyUtil.genUniqueKey());
        orderMaster.setUserId(userId);
        orderMaster.setShippingId(shippingId);
        orderMaster.setPayment(payment);
        orderMaster.setPaymentType(TipsBusiness.ONLINE_PAY.getCode());
        orderMaster.setPostage(10);
        orderMaster.setStatus(TipsBusiness.NO_PAY.getCode());
        orderMaster.setPaymentTime(null);
        orderMaster.setSendTime(null);
        orderMaster.setEndTime(null);
        orderMaster.setCloseTime(null);
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        return orderMaster;
    }

    /**
     * 【复用】创建orderDetail
     * @param userId 用户id
     * @param souvenir 纪念品
     * @param orderId 订单id
     * @param totalPrice 总价
     * @param count 购买数量
     * @return OrderDetail
     */
    private OrderDetail createOrderDetail(String userId,Souvenir souvenir, Long orderId,BigDecimal totalPrice,Integer count) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(KeyUtil.genUniqueKey());
        orderDetail.setUserId(userId);
        orderDetail.setSouvenirId(souvenir.getSouvenirId());
        orderDetail.setSouvenirName(souvenir.getName());
        orderDetail.setSouvenirImage(souvenir.getMainImage());
        orderDetail.setCurrentUnitPrice(souvenir.getPrice());
        orderDetail.setQuantity(count);
        orderDetail.setTotalPrice(totalPrice);
        orderDetail.setCreateTime(new Date());
        orderDetail.setUpdateTime(new Date());
        orderDetail.setOrderId(orderId);
        return orderDetail;
    }




//
//    /**
//     * lamada表达式
//     *
//     * @param buyerOpenid
//     * @param pageable
//     * @return
//     */
//    @Override
//    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
//        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
////        PageImpl<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>();
//        List<OrderMaster> content = orderMasterPage.getContent();
//        List<OrderDTO> orderDTOList = convert(content);
////
//        PageImpl<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
//        return orderDTOPage;
//    }
//
//    public List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
//        List<OrderDTO> orderDTOList = new ArrayList<>();
//        for (OrderMaster orderMaster : orderMasterList) {
//            OrderDTO orderDTO = new OrderDTO();
//            BeanUtils.copyProperties(orderMaster, orderDTO);
//            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
//            orderDTO.setOrderDetailList(orderDetailList);
//            orderDTOList.add(orderDTO);
//        }
//        return orderDTOList;
//    }
//
//    @Override
//    @Transactional
//    public OrderDTO cancel(OrderDTO orderDTO) {
//        OrderMaster orderMaster = new OrderMaster();
//        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
//            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
//            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
//        BeanUtils.copyProperties(orderDTO, orderMaster);
//
//        //保存/更新了还要记得判空
//        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
//        if (updateResult == null) {
//            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
//            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
//        }
//
//        //返回库存
//        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
//            log.error("【取消订单】订单中无商品详情，orderDTO={}", orderDTO);
//            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
//        }
//        List<CartDTO> cartDTOList = new ArrayList<>();
//        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
//        }
//        souvenirService.increaseStock(cartDTOList);
//
////        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
//        // TODO: 2019/9/10
////        }
//        return orderDTO;
//    }
//
//    @Override
//    public OrderDTO finish(OrderDTO orderDTO) {
//        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
//            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
//            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderDTO, orderMaster);
//        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
//        if (updateResult == null) {
//            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
//            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
//        }
//
//        //推送微信模板消息
//        pushMessageService.orderStatus(orderDTO);
//        return orderDTO;
//    }
//
//    @Override
//    @Transactional
//    public OrderDTO paid(OrderDTO orderDTO) {
//        //判断订单状态
//        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
//            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
//            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//
//        //判断支付状态
//        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
//            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
//            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
//        }
//
//        //修改支付状态
//        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderDTO, orderMaster);
//        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
//        if (updateResult == null) {
//            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
//            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
//        }
//
//        return orderDTO;
//    }
//
//    @Override
//    public Page<OrderDTO> findList(Pageable pageable) {
//        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
//        List<OrderMaster> content = orderMasterPage.getContent();
//        List<OrderDTO> orderDTOList = this.convert(content);
//        PageImpl<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
//        return orderDTOPage;
//    }
}
