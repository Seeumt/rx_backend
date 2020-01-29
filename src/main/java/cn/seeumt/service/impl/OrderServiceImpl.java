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

import static com.sun.xml.internal.ws.api.message.Packet.State.ServerResponse;

@Service
@Slf4j
@Transactional
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

    private void cleanCart(String userId, Integer souvenirId) {
        int i = cartService.deleteByUserIdAndSouvenirId(userId, souvenirId);
        if (i < 0) {
            throw new TipsException(TipsFlash.CLEAN_CART_FAILED);
        }
    }

    private void reduceSouvenirStock(OrderDetail orderDetail) {
        Souvenir souvenir = souvenirMapper.selectById(orderDetail.getSouvenirId());
        souvenir.setStock(souvenir.getStock() - orderDetail.getQuantity());
        souvenirMapper.updateById(souvenir);
    }


    private BigDecimal getOrderDetailListTotalPrice(List<OrderDetail> orderDetailList) {
        BigDecimal payment = new BigDecimal("0");
        for(OrderDetail orderDetail : orderDetailList){
            payment = BigDecimalUtil.add(payment.doubleValue(),orderDetail.getTotalPrice().doubleValue());
        }
        return payment;
    }

    /**
     * 得到有效的购物车清单 并放入到一个子预订单列表里
     * @param userId
     * @param carts
     * @return ResultVO(技巧)
     */
    // TODO: 2020/1/29 skills
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
        PageInfo<OrderDTO> orderDTOPageInfo = new PageInfo<>(orderDTOList);
        return orderDTOPageInfo;
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

    @Override
    public OrderMaster insertOrderMaster(String userId, Integer shippingId, BigDecimal payment) {
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
        int insert = orderMasterMapper.insert(orderMaster);
        if (insert > 0) {
            return orderMaster;
        }
        return null;
    }





//    @Override
//    @Transactional
//    public OrderDTO create(OrderDTO orderDTO) {
//        String orderId = KeyUtil.genUniqueKey();
//        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//
//        //1.查询商品（数量、价格）判断库存
//        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
//        List<CartDTO> cartDTOList = new ArrayList<>();
//        for (OrderDetail orderDetail : orderDetailList) {
//            ProductInfo souvenirInfo = souvenirService.findOne(orderDetail.getProductId());
//            if (souvenirInfo == null) {
//                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
////                throw new ResponseBankException();
//            }
//            //2.计算订单总价
//            orderAmount = souvenirInfo.getProductPrice()
//                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
//                    .add(orderAmount);
//
//            orderDetail.setOrderId(orderId);
//            orderDetail.setDetailId(KeyUtil.genUniqueKey());
//            BeanUtils.copyProperties(souvenirInfo, orderDetail);
//            //订单详情写入数据库
//            orderDetailRepository.save(orderDetail);
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
//
//            System.out.println(cartDTOList);
//        }
//
//        //3.写入订单数据库
//        OrderMaster orderMaster = new OrderMaster();
//
//        BeanUtils.copyProperties(orderDTO, orderMaster);
//
//        orderMaster.setOrderId(orderId);
//
//        orderMaster.setOrderAmount(orderAmount);
//        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
//        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
//        orderMasterRepository.save(orderMaster);
//
//
//        //4.扣库存
//        souvenirService.decreaseStock(cartDTOList);
//        return orderDTO;
//    }
//
//    @Override
//    public OrderDTO findOne(String orderId) {
//        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
//        if (orderMaster == null) {
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
//        if (CollectionUtils.isEmpty(orderDetailList)) {
//            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
//        }
//
//        OrderDTO orderDTO = new OrderDTO();
//        BeanUtils.copyProperties(orderMaster, orderDTO);
//        orderDTO.setOrderDetailList(orderDetailList);
//
//        return orderDTO;
//    }
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
