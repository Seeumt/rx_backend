package cn.seeumt.controller;

import cn.seeumt.dto.OrderDTO;
import cn.seeumt.service.OrderService;
import cn.seeumt.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 订单
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/29 11:53
 */
@Api(tags = {"订单"})
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     * @param userId 用户主键id
     * @param shippingId 快递id
     * @return ResultVO
     */
    @PostMapping("/createFromCart")
    @ResponseBody
    public ResultVO create(String userId, Integer shippingId){
        log.info("用户 {} 创建订单 订单号:{}",userId,shippingId);
        return orderService.createOrder(userId,shippingId);
    }

    /**
     * 立即购买
     * @param userId 用户id
     * @param shippingId 收获地址id
     * @param souvenirId 纪念品id
     * @param count 购买数量
     * @return ResultVO
     */
    @PostMapping("/buyNow")
    @ResponseBody
    public ResultVO buyNow(String userId, Integer shippingId, Integer souvenirId, Integer count) {
        return orderService.createOrderNow(userId, shippingId, souvenirId, count);
    }

    /**
     * 查询某一订单
     * @param orderId 订单主键
     * @return OrderDTO
     */
    @GetMapping("/find")
    public OrderDTO findOne(Long orderId) {
        return orderService.findOne(orderId);
    }

    /**
     * 分页查询所有订单
     * @param userId 用户id
     * @param num 当前页码数
     * @param size 每页条数
     * @return ResultVO
     */
    @PostMapping("/list")
    public ResultVO list(@RequestParam(value = "userId")String userId,
                         @RequestParam(value = "num",required = false,defaultValue = "1") int num,
                         @RequestParam(value = "size", required = false, defaultValue = "3")int size) {

        PageInfo<OrderDTO> orderDtoPageInfo = orderService.list(num, size, userId);
        return ResultVO.success(orderDtoPageInfo);
    }



}
